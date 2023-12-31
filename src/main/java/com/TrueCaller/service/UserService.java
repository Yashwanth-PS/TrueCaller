package com.TrueCaller.service;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.dto.UserRegisterRequestDTO;
import com.TrueCaller.dto.UserRegisterResponseDTO;
import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.UserType;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserRegisterResponseDTO> registerUser(UserRegisterRequestDTO userRegisterRequestDTO, UserType userType);
    ResponseEntity<UserPhoneResponseDTO>  identifyCaller(UserPhoneRequestDTO userPhoneRequestDT);
}