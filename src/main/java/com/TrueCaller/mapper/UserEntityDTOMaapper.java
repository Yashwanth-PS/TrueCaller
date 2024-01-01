package com.TrueCaller.mapper;

import com.TrueCaller.dto.UserRegisterRequestDTO;
import com.TrueCaller.dto.UserRegisterResponseDTO;
import com.TrueCaller.model.User;

import java.util.ArrayList;

public class UserEntityDTOMaapper {
    public static User getUserEntityFromUserRegisterRequestDTO(UserRegisterRequestDTO userRegisterRequestDTO) {
        User user = new User();
        user.setName(userRegisterRequestDTO.getName());
        user.setEmail(userRegisterRequestDTO.getEmail());
        user.setContacts(new ArrayList<>());
        return user;
    }

    public static UserRegisterResponseDTO getUserRegisterResponseDTOFromUserEntity(User user) {
        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setName(user.getName());
        userRegisterResponseDTO.setEmail(user.getEmail());
        userRegisterResponseDTO.setUserType(user.getUserType());
        return userRegisterResponseDTO;
    }
}