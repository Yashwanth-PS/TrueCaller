package com.TrueCaller.service;

import com.TrueCaller.dto.UserPhoneRequestDTO;
import com.TrueCaller.dto.UserPhoneResponseDTO;
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

import static com.TrueCaller.mapper.UserEntityDTOMaapper.getUserEntityFromUserRegisterRequestDTO;
import static com.TrueCaller.mapper.UserEntityDTOMaapper.getUserRegisterRsponseDTOFromUserEntity;

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
    public Contact addContact(String phoneNumber, ContactType contactType) {
        // Perform validation or additional logic if needed
        Contact contact = new Contact();
        contact.setPhoneNumber(phoneNumber);
        contact.setContactType(contactType);
        return contactRepository.save(contact);
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
            responseDTO.setResponseMessage("SUCCESS: User Registration Successful");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setResponseCode(500);
            responseDTO.setResponseMessage("Please Use a Valid Email and Password");
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @Override
    public void unblockContact(Long userId, String phoneNumber) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("This User does not exist");
        }
        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(phoneNumber);
        if (contactOptional.isEmpty()) {
            throw new ContactNotFoundException("This Contact does not exist");
        }
        if (userOptional.get().getBlockedContacts().stream().parallel().noneMatch(contact -> contact.getId().equals(contactOptional.get().getId()))) {
            throw new ContactNotFoundException("The Contact is Not Blocked");
        }
        User user = userOptional.get();
        user.getBlockedContacts().remove(contactOptional.get());
        userRepository.save(user);
    }

    @Override
    public void reportSpam(Long contactId) {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if (contactOptional.isEmpty()) {
            throw new ContactNotFoundException("This Contact does not exist");
        }
        Contact updatedSpamCount = contactOptional.get();
        updatedSpamCount.setSpamCount(contactOptional.get().getSpamCount() + 1);
        contactRepository.save(updatedSpamCount);
    }

    @Override
    public void blackListContact(Long userId, String phoneNumber) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("This User does not exist");
        }
        User user = userOptional.get();
        if (!user.getUserType().equals(UserType.CONTACT_MANAGER)){
            throw new IllegalOperationException("Only Contact Manager Can Blacklist a User");
        }
        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(phoneNumber);
        if (contactOptional.isEmpty()) {
            throw new ContactNotFoundException("This Contact does not exist");
        }
        Contact contact = contactOptional.get();
        contact.setContactType(ContactType.BLACK_LISTED);
        contactRepository.save(contact);
    }
}