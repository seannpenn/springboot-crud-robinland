package com.robinland.pos.RobinlandPos.functions.role.controller;

import com.robinland.pos.RobinlandPos.functions.role.service.RoleServiceInterface;
import com.robinland.pos.RobinlandPos.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleServiceInterface roleService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getAllRoles() {
        return ApiResultModel.builder()
                .isSuccess(true)
                .length(roleService.getAllRoles().size())
                .resultData(roleService.getAllRoles())
                .message("Successfully retrieved all role data")
                .build();
    }
}
