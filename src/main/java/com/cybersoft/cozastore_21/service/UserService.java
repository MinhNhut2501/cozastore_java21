package com.cybersoft.cozastore_21.service;

import com.cybersoft.cozastore_21.entity.UserEntity;
import com.cybersoft.cozastore_21.exception.CustomException;
import com.cybersoft.cozastore_21.payload.request.SignupRequest;
import com.cybersoft.cozastore_21.repository.UserRepository;
import com.cybersoft.cozastore_21.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(SignupRequest request) {
        boolean isSuccess = false;
        try {
            UserEntity user = new UserEntity();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            userRepository.save(user);
            isSuccess = true;
        }catch (Exception e){
            throw new CustomException("Loi them user " + e.getMessage());
        }

        return isSuccess;
    }
}
