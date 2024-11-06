package com.robinland.pos.RobinlandPos.functions.user.service;

import com.robinland.pos.RobinlandPos.functions.user.dto.UserRequestDto;
import com.robinland.pos.RobinlandPos.functions.user.repository.UserRepository;
import com.robinland.pos.RobinlandPos.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Optional<User> getUser(Long userId) throws Exception{
        Optional<User> existingUser = userRepository.findById(userId);

        if(ObjectUtils.isEmpty(existingUser)){
            throw new Exception("No user found");
        }

        return existingUser;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User addUser(UserRequestDto requestDto) throws Exception {
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
        } catch (Exception e) {
            throw new Exception("Failed to add user data");
        }

        return user;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User updateUser(Long id, UserRequestDto requestDto) throws Exception {
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

        updatedUser = userRepository.save(updatedUser);

        return updatedUser;
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
}
