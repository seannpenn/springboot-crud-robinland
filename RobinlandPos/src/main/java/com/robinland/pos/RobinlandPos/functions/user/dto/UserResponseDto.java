package com.robinland.pos.RobinlandPos.functions.user.dto;

import com.robinland.pos.RobinlandPos.model.Role;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private List<Role> userRoles;
}
