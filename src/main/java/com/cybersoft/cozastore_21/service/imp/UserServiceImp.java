package com.cybersoft.cozastore_21.service.imp;

import com.cybersoft.cozastore_21.payload.request.SignupRequest;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);
}
