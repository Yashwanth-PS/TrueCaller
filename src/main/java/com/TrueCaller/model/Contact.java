package com.TrueCaller.model;

import com.TrueCaller.model.constants.ContactType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String phoneNumber;

    private int spamCount;

    @Enumerated(EnumType.STRING)
    private ContactType contactType;
}