package com.TrueCaller.service;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.model.Contact;
import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.ContactType;
import org.springframework.http.ResponseEntity;

public interface ContactService {

    Contact addContact(String phoneNumber, ContactType contactType);

    ResponseEntity<UserPhoneResponseDTO> blockContact(UserPhoneRequestDTO userPhoneRequestDTO);

    ResponseEntity<UserPhoneResponseDTO> unblockContact(UserPhoneRequestDTO userPhoneRequestDTO);

    void reportSpam(Long contactId);

    void blackListContact(Long userId, String phoneNumber);

}