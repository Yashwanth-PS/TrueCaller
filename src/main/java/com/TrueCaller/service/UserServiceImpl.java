package com.TrueCaller.service;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.dto.UserRegisterRequestDTO;
import com.TrueCaller.dto.UserRegisterResponseDTO;
import com.TrueCaller.exception.UserNotFoundException;
import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.UserType;
import com.TrueCaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.TrueCaller.mapper.UserEntityDTOMaapper.getUserEntityFromUserRegisterRequestDTO;
import static com.TrueCaller.mapper.UserEntityDTOMaapper.getUserRegisterRsponseDTOFromUserEntity;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<UserRegisterResponseDTO> registerUser(UserRegisterRequestDTO userRegisterRequestDTO, UserType userType) {
        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();
        try { // You may need to implement the actual sign-in logic based on your requirements
            User user = getUserEntityFromUserRegisterRequestDTO(userRegisterRequestDTO);
            user.setUserType(userType);
            user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));
            userRepository.save(user);
            responseDTO = getUserRegisterRsponseDTOFromUserEntity(user);
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS: User Registration Successful");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Please Use a Valid Email and Password");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @Override
    public  ResponseEntity<UserPhoneResponseDTO> identifyCaller(UserPhoneRequestDTO userPhoneRequestDTO) {
        UserPhoneResponseDTO responseDTO = new UserPhoneResponseDTO();
        try { // You may need to implement the actual sign-in logic based on your requirements
            Optional<User> userOptional = userRepository.findByPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("This User does not exist");
            }
            responseDTO.setName(userOptional.get().getName());
            responseDTO.setPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS: User Found Successfully");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("The Phone Number does not exist in our Data Base");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }
}