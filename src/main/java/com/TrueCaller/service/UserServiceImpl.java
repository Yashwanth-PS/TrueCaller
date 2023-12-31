package com.TrueCaller.service;

import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.UserType;
import com.TrueCaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String userName, String userPhoneNumber, String userEmail, UserType userType, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUserName(userName);
        user.setUserPhoneNumber(userPhoneNumber);
        user.setUserEmail(userEmail);
        user.setUserType(userType);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}