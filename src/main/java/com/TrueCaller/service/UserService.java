package com.TrueCaller.service;

import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.UserType;

public interface UserService {
    String registerUser(String userName, String userPhoneNumber, String userEmail, UserType userType, String password);
    String identifyCaller(String phoneNumber);
}