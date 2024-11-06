package com.robinland.pos.RobinlandPos.model;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApiResultModel {
    private Boolean isSuccess;
    private String message;
    private Object resultData;
    private Integer length;
}
