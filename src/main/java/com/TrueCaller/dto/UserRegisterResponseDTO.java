package com.TrueCaller.dto;

import com.TrueCaller.model.constants.UserType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRegisterResponseDTO {
    private String name;
    private String email;
    private UserType userType;
    private int responseCode;
    private String responseMessage;
}
