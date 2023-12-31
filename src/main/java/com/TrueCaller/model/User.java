package com.TrueCaller.model;

import com.TrueCaller.model.constants.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String userPhoneNumber;
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}