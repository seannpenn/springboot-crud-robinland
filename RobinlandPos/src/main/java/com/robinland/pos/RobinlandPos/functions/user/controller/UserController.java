package com.robinland.pos.RobinlandPos.functions.user.controller;

import com.robinland.pos.RobinlandPos.functions.user.dto.UserRequestDto;
import com.robinland.pos.RobinlandPos.functions.user.service.UserService;
import com.robinland.pos.RobinlandPos.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getAllUsers() {
        return ApiResultModel.builder()
                .isSuccess(true)
                .length(userService.getAllUsers().size())
                .resultData(userService.getAllUsers())
                .build();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getUser(@PathVariable("id") Long id) {
        return ApiResultModel.builder()
                .isSuccess(true)
                .length(1)
                .resultData(userService.getUser(id))
                .build();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel addUser(UserRequestDto userRequestDto){
        return ApiResultModel.builder()
                .isSuccess(true)
                .length(1)
                .resultData(userService.addUser(userRequestDto))
                .build();
    }
}
