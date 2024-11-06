package com.robinland.pos.RobinlandPos.functions.user.service;

import com.robinland.pos.RobinlandPos.functions.user.dto.UserRequestDto;
import com.robinland.pos.RobinlandPos.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServiceInterface {

    public List<User> getAllUsers();

    public Optional<User> getUser(Long userId);

    public User addUser(UserRequestDto requestDto);
}
