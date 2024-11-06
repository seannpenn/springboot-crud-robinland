package com.robinland.pos.RobinlandPos.functions.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDto {

    private String address1;

    private String address2;

    private String address3;

    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

    private String mobile;

    private String password;

    private String phone;

    private String tin;
}
