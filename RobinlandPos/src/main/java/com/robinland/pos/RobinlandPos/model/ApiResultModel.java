package com.robinland.pos.RobinlandPos.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ApiResultModel {
    private Boolean isSuccess;
    private String message;
    private Object messageParams;
    private Object resultData;
    private List<String> errorMessages;
    private Object errorCodes;
    private String exceptionType;
    private Integer length;
}
