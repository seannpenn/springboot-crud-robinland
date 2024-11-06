package com.robinland.pos.RobinlandPos.functions.user.service;

import com.robinland.pos.RobinlandPos.functions.user.dto.UserRequestDto;
import com.robinland.pos.RobinlandPos.functions.user.dto.UserResponseDto;
import com.robinland.pos.RobinlandPos.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServiceInterface {

    public List<User> getAllUsers();

    public UserResponseDto getUser(Long userId)  throws Exception;

    public UserResponseDto addUser(UserRequestDto requestDto) throws Exception;

    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) throws Exception;

    void deleteUser(Long id) throws Exception;
}
