package com.TrueCaller.service;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.model.Contact;
import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.ContactType;
import org.springframework.http.ResponseEntity;

public interface ContactService {

    ResponseEntity<UserPhoneResponseDTO> addContact(UserPhoneRequestDTO userPhoneRequestDTO);

    ResponseEntity<UserPhoneResponseDTO> blockContact(UserPhoneRequestDTO userPhoneRequestDTO);

    ResponseEntity<UserPhoneResponseDTO> unblockContact(UserPhoneRequestDTO userPhoneRequestDTO);

    ResponseEntity<UserPhoneResponseDTO> reportSpam(UserPhoneRequestDTO userPhoneRequestDTO);

    void blackListContact(Long userId, String phoneNumber);

}