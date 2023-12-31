package com.TrueCaller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserPhoneResponseDTO {
    private String name;
    private String phoneNumber;
    private int responseCode;
    private String responseMessage;

}
