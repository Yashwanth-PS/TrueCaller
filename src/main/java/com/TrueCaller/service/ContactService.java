package com.TrueCaller.service;

import com.TrueCaller.model.Contact;
import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.ContactType;

public interface ContactService {

    Contact addContact(String phoneNumber, ContactType contactType);

    void blockContact(Long userId, String phoneNumber);

    void unblockContact(Long userId, String phoneNumber);

    void reportSpam(Long contactId);

    void blackListContact(Long userId, String phoneNumber);

}