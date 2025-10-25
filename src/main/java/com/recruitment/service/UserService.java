package com.recruitment.service;

import com.recruitment.model.User;
import com.recruitment.repository.UserRepository;
import com.recruitment.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignupRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setProfileHeadline(request.getProfileHeadline());
        user.setUserType(request.getUserType());
        return userRepository.save(user);
    }
}
