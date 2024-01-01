package com.TrueCaller.model;

import com.TrueCaller.model.constants.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    List<Contact> contacts;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany
    List<Contact> blockedContacts;

    @OneToMany
    List<Contact> spammedContacts;
}