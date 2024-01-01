package com.TrueCaller.controller;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.dto.UserRegisterRequestDTO;
import com.TrueCaller.dto.UserRegisterResponseDTO;
import com.TrueCaller.model.constants.UserType;
import com.TrueCaller.service.ContactService;
import com.TrueCaller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @PostMapping("/user/registration")
    public ResponseEntity<UserRegisterResponseDTO> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        ResponseEntity<UserRegisterResponseDTO> response = userService.registerUser(userRegisterRequestDTO, UserType.NORMAL_USER);
        return response;
    }
    /* POST: http://localhost:8181/api/users/user/registration
    {
        "name": "Yash",
        "phoneNumber": "9876543210",
        "email": "yash@example.com",
        "password": "securePassword123"
    } */

    @PostMapping("/manager/registration")
    public ResponseEntity<UserRegisterResponseDTO> registerManager(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.registerUser(userRegisterRequestDTO, UserType.CONTACT_MANAGER);
    }

    @GetMapping("/user/identification")
    public ResponseEntity<UserPhoneResponseDTO> identifyUser(@RequestBody UserPhoneRequestDTO userPhoneRequestDTO) {
        return userService.identifyCaller(userPhoneRequestDTO);
    }
    /* GET: http://localhost:8181/api/users/user/identification
    {
        "userId": "10",
        "phoneNumber": "9876543210"
    } */
}