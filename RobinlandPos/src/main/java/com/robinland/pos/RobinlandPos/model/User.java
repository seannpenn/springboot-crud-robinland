package com.robinland.pos.RobinlandPos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Builder
@Getter
@Setter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String userName;

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

    @Column(length = 64, unique = true)
    private String email;

    @Column(length = 16)
    private String tin;

    @OneToMany(mappedBy = "user",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            }, fetch = FetchType.LAZY)
    @JsonBackReference()
    private List<UserRole> roles = new ArrayList<>();
}
