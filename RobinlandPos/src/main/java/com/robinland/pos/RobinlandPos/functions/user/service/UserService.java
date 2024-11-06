package com.robinland.pos.RobinlandPos.functions.user.service;

import com.robinland.pos.RobinlandPos.functions.user.dto.UserRequestDto;
import com.robinland.pos.RobinlandPos.functions.user.dto.UserResponseDto;
import com.robinland.pos.RobinlandPos.functions.user.repository.UserRepository;
import com.robinland.pos.RobinlandPos.model.Role;
import com.robinland.pos.RobinlandPos.model.User;
import com.robinland.pos.RobinlandPos.model.UserRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserResponseDto getUser(Long userId) throws Exception{
        UserResponseDto responseDto = new UserResponseDto();
        Optional<User> existingUser = userRepository.findById(userId);

        if(ObjectUtils.isEmpty(existingUser)) {
            throw new Exception("No user found");
        }

        existingUser.ifPresent(user -> {
            BeanUtils.copyProperties(user, responseDto);

            responseDto.setUserRoles(extractRolesFromUserRoles(user));
        });

        return responseDto;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserResponseDto addUser(UserRequestDto requestDto) throws Exception {
        UserResponseDto responseDto = new UserResponseDto();
        User user = new User();
        LocalDateTime dateTimeNow = LocalDateTime.now();
        BeanUtils.copyProperties(requestDto, user);

        user.setCreatedDate(dateTimeNow);
        user.setUpdatedDate(dateTimeNow);

        if (isEmailUnique(user.getEmail())) {
            throw new Exception("Email is already taken.");
        }

        try {
            user = userRepository.save(user);
            BeanUtils.copyProperties(user, responseDto);
        } catch (Exception e) {
            throw new Exception("Failed to add user data");
        }

        return responseDto;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) throws Exception {
        UserResponseDto responseDto = new UserResponseDto();
        User oldUser = new User();
        User updatedUser = new User();
        Optional<User> existingUser = userRepository.findById(id);

        if(!ObjectUtils.isEmpty(existingUser)){
            BeanUtils.copyProperties(existingUser, oldUser);
            BeanUtils.copyProperties(requestDto, oldUser);
            BeanUtils.copyProperties(oldUser, updatedUser);

        }else{
            throw new Exception("User not found");
        }

        if (isEmailUnique(updatedUser.getEmail())) {
            throw new Exception("Email is already taken.");
        }
        try {
            updatedUser = userRepository.save(updatedUser);
            BeanUtils.copyProperties(updatedUser, responseDto);
        }catch(Exception e){
            throw new Exception("Failed to update user data");
        }


        return responseDto;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteUser(Long id) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);

        if(!ObjectUtils.isEmpty(existingUser)){
            userRepository.deleteById(id);
        }else{
            throw new Exception("User not found");
        }
    }

    private boolean isEmailUnique(String email) {
        return !ObjectUtils.isEmpty(userRepository.findByEmail(email));
    }

    private List<Role> extractRolesFromUserRoles(User user) {
        return user.getRoles().stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }
}
