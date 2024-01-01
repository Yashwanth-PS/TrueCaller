package com.TrueCaller.controller;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.service.ContactService;
import com.TrueCaller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @PostMapping("/contact/block")
    public ResponseEntity<UserPhoneResponseDTO> blockContact(@RequestBody UserPhoneRequestDTO userPhoneRequestDTO) {
        return contactService.blockContact(userPhoneRequestDTO);
    }
/* http://localhost:8181/api/contacts/contact/block
{
    "userId": "2",
    "phoneNumber": "9876543210"
} */

    @PostMapping("/contact/unblock")
    public ResponseEntity<UserPhoneResponseDTO> unblockContact(@RequestBody UserPhoneRequestDTO userPhoneRequestDTO) {
        return contactService.unblockContact(userPhoneRequestDTO);
    }

/* http://localhost:8181/api/contacts/contact/block
{
    "userId": "2",
    "phoneNumber": "9876543210"
} */

    @PostMapping("/contact/add")
    public ResponseEntity<UserPhoneResponseDTO> addContact(@RequestBody UserPhoneRequestDTO userPhoneRequestDTO) {
        return contactService.addContact(userPhoneRequestDTO);
    }

/* http://localhost:8181/api/contacts/contact/add
{
    "userId": "2",
    "phoneNumber": "9876543211"
} */
}