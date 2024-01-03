package com.TrueCaller.service;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
import com.TrueCaller.exception.ContactAlreadySpammedException;
import com.TrueCaller.exception.ContactNotFoundException;
import com.TrueCaller.exception.IllegalOperationException;
import com.TrueCaller.exception.UserNotFoundException;
import com.TrueCaller.model.Contact;
import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.ContactType;
import com.TrueCaller.model.constants.UserType;
import com.TrueCaller.repository.ContactRepository;
import com.TrueCaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserPhoneResponseDTO> addContact(UserPhoneRequestDTO userPhoneRequestDTO) {
        // Perform validation or additional logic if needed
        UserPhoneResponseDTO responseDTO = new UserPhoneResponseDTO();
        try {
            Contact contact = new Contact();
            ;
            contact.setPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            contact.setContactType(ContactType.NORMAL);
            contactRepository.save(contact);
            responseDTO.setName("Unregistered User");
            responseDTO.setPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS: Successfully Added the Contact");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Could Not Add the Contact");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @Override
    public ResponseEntity<UserPhoneResponseDTO> blockContact(UserPhoneRequestDTO userPhoneRequestDTO) {
        UserPhoneResponseDTO responseDTO = new UserPhoneResponseDTO();
        try { // You may need to implement the actual logic based on your requirements
            Optional<User> userOptional = userRepository.findById(userPhoneRequestDTO.getUserId());
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("This User does not exist");
            }
            Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            if (contactOptional.isEmpty()) {
                throw new ContactNotFoundException("This Contact does not exist");
            }
            User user = userOptional.get();
            user.getBlockedContacts().add(contactOptional.get());
            userRepository.save(user);
            responseDTO.setName(user.getName());
            responseDTO.setPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS: Successfully Blocked the User");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Could Not Block the User");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @Override
    public ResponseEntity<UserPhoneResponseDTO> unblockContact(UserPhoneRequestDTO userPhoneRequestDTO) {
        UserPhoneResponseDTO responseDTO = new UserPhoneResponseDTO();
        try { // You may need to implement the actual logic based on your requirements
            Optional<User> userOptional = userRepository.findById(userPhoneRequestDTO.getUserId());
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("This User does not exist");
            }
            Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            if (contactOptional.isEmpty()) {
                throw new ContactNotFoundException("This Contact does not exist");
            }
            if (userOptional.get().getBlockedContacts().stream().parallel().noneMatch(contact -> contact.getId().equals(contactOptional.get().getId()))) {
                throw new ContactNotFoundException("The Contact is Not Blocked");
            }
            User user = userOptional.get();
            user.getBlockedContacts().remove(contactOptional.get());
            userRepository.save(user);
            responseDTO.setName(user.getName());
            responseDTO.setPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS: Successfully Unblocked the User");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Could Not Unblock the User");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @Override
    public ResponseEntity<UserPhoneResponseDTO> reportSpam(UserPhoneRequestDTO userPhoneRequestDTO) {
        UserPhoneResponseDTO responseDTO = new UserPhoneResponseDTO();
        try { // You may need to implement the actual logic based on your requirements
            Optional<User> userOptional = userRepository.findById(userPhoneRequestDTO.getUserId());
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("This User does not exist");
            }
            Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            if (contactOptional.isEmpty()) {
                throw new ContactNotFoundException("This Contact does not exist");
            }
            if (userOptional.get().getSpammedContacts().contains(contactOptional.get())) {
                if (contactOptional.isEmpty()) {
                    throw new ContactAlreadySpammedException("This Contact is already spammed");
                }
            }
            User user = userOptional.get();
            user.getSpammedContacts().add(contactOptional.get());
            Contact updatedSpamCount = contactOptional.get();
            updatedSpamCount.setSpamCount(contactOptional.get().getSpamCount() + 1);
            userRepository.save(user);
            contactRepository.save(updatedSpamCount);
            responseDTO.setName(updatedSpamCount.getUser().getName());
            responseDTO.setPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS: Successfully Reported the User as Spam");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Could Not Reported the User as Spam");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @Override
    public ResponseEntity<UserPhoneResponseDTO> blackListContact(UserPhoneRequestDTO userPhoneRequestDTO) {
        UserPhoneResponseDTO responseDTO = new UserPhoneResponseDTO();
        try { // You may need to implement the actual logic based on your requirements
            Optional<User> userOptional = userRepository.findById(userPhoneRequestDTO.getUserId());
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("This User does not exist");
            }
            User user = userOptional.get();
            if (!user.getUserType().equals(UserType.CONTACT_MANAGER)) {
                throw new IllegalOperationException("Only Contact Manager Can Blacklist a User");
            }
            Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            if (contactOptional.isEmpty()) {
                throw new ContactNotFoundException("This Contact does not exist");
            }
            Contact contact = contactOptional.get();
            contact.setContactType(ContactType.BLACK_LISTED);
            contactRepository.save(contact);
            responseDTO.setPhoneNumber(userPhoneRequestDTO.getPhoneNumber());
            responseDTO.setResponseCode(200);
            responseDTO.setResponseMessage("SUCCESS: Successfully Reported the User as Spam");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Could Not Reported the User as Spam");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }
}