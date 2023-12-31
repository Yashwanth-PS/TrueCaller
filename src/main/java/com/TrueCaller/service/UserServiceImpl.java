package com.TrueCaller.service;

import com.TrueCaller.exception.UserNotFoundException;
import com.TrueCaller.model.User;
import com.TrueCaller.model.constants.UserType;
import com.TrueCaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public String registerUser(String userName, String userPhoneNumber, String userEmail, UserType userType, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUserName(userName);
        user.setUserPhoneNumber(userPhoneNumber);
        user.setUserEmail(userEmail);
        user.setUserType(userType);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        String message = "User Registration Successful";
        return message;
    }

    @Override
    public String identifyCaller(String phoneNumber) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("This User does not exist");
        }
        String userName = userOptional.get().getUserName();
        return userName;
    }
}