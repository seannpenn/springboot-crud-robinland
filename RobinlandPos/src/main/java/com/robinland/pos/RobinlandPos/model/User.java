package com.robinland.pos.RobinlandPos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@Builder
@Getter
@Setter
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    @Column(columnDefinition = "TINYINT(1)")
    @JsonProperty("isActive")
    private boolean isActive;

    @Column(length = 120)
    private String address1;

    @Column(length = 120)
    private String address2;

    @Column(length = 120)
    private String address3;

    @Column(length = 13)
    private String phone;

    @Column(length = 13)
    private String mobile;

    @Column(length = 16)
    private String fax;

    @Column(length = 64)
    private String email;

    @Column(length = 16)
    private String tin;

}
