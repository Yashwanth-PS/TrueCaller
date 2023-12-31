package com.TrueCaller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPhoneRequestDTO {
    private Long userId;
    private String phoneNumber;
}
