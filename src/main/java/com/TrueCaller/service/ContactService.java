package com.TrueCaller.service;

import com.TrueCaller.model.Contact;
import com.TrueCaller.model.constants.ContactType;

public interface ContactService {

    Contact addContact(String phoneNumber, ContactType contactType);

    void blockContact(Long contactId);

    void unblockContact(Long contactId);

    void reportSpam(Long contactId);

    String identifyCaller(String phoneNumber);

    void blackListContact(Long contactId);

}