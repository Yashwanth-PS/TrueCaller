package com.TrueCaller.mapper;

import com.TrueCaller.dto.UserRegisterRequestDTO;
import com.TrueCaller.dto.UserRegisterResponseDTO;
import com.TrueCaller.model.User;

public class UserEntityDTOMaapper {
    public static User getUserEntityFromUserRegisterRequestDTO(UserRegisterRequestDTO userRegisterRequestDTO){
        User user = new User();
        user.setName(userRegisterRequestDTO.getName());
        user.setPhoneNumber(userRegisterRequestDTO.getPhoneNumber());
        user.setEmail(userRegisterRequestDTO.getEmail());
        return user;
    }

    public static UserRegisterResponseDTO getUserRegisterRsponseDTOFromUserEntity(User user){
        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO .setName(user.getName());
        userRegisterResponseDTO .setPhoneNumber(user.getPhoneNumber());
        userRegisterResponseDTO .setEmail(user.getEmail());
        userRegisterResponseDTO .setUserType(user.getUserType());
        return userRegisterResponseDTO;
    }
}