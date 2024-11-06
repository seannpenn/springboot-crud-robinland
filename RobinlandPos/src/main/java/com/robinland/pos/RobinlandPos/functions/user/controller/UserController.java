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
                .message("Successfully retrieved all user data")
                .build();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getUser(@PathVariable("id") Long id) {
        boolean isSuccess = false;
        String message = "";
        int length = 0;
        Object resultData = null;

        try {
             resultData = userService.getUser(id);
             isSuccess = true;
             message = "Successfully retrieved user data";
             length = 1;
        } catch (Exception e) {
            message = "Failed to retrieve user data: " + e.getMessage();
        }

        return ApiResultModel.builder()
                .isSuccess(isSuccess)
                .message(message)
                .length(length)
                .resultData(resultData)
                .build();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel addUser(@RequestBody UserRequestDto userRequestDto){

        boolean isSuccess = false;
        String message = "";
        int length = 0;
        Object resultData = null;

        try {
            resultData = userService.addUser(userRequestDto);
            message = "Successfully added user data";
            isSuccess = true;
            length = 1;
        } catch (Exception e) {
            message = "Failed to add user data: " + e.getMessage();
        }

        return ApiResultModel.builder()
                .isSuccess(isSuccess)
                .length(length)
                .message(message)
                .resultData(resultData)
                .build();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel updateBankMasters(@PathVariable("id") Long id,
                                            @RequestBody UserRequestDto userRequestDto){

        boolean isSuccess = false;
        String message = "";
        int length = 0;
        Object resultData = null;

        try {
            resultData = userService.updateUser(id, userRequestDto);
            message = "Successfully updated user data";
            isSuccess = true;
            length = 1;
        } catch (Exception e) {
            message = "Failed to update user data: " + e.getMessage();
        }

        return ApiResultModel.builder()
                .isSuccess(isSuccess)
                .length(length)
                .message(message)
                .resultData(resultData)
                .build();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel deleteUser(@PathVariable("id") Long id) {
        boolean isSuccess = false;
        String message = "";

        try {
            userService.deleteUser(id);
            message = "Successfully deleted user data";
            isSuccess = true;
        } catch (Exception e) {
            message = "Failed to update user data: " + e.getMessage();
        }

        return ApiResultModel.builder()
                .isSuccess(isSuccess)
                .message(message)
                .build();
    }
}
