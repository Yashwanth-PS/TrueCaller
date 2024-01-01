package com.TrueCaller.controller;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.service.ContactService;
import com.TrueCaller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/block")
    public ResponseEntity<UserPhoneResponseDTO> blockContact(@RequestBody UserPhoneRequestDTO userPhoneRequestDTO) {
        return contactService.blockContact(userPhoneRequestDTO);
    }
}
