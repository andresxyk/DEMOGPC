package com.mx.eval.demogpc.service;



import java.util.List;

import com.mx.eval.demogpc.dto.UserRequest;
import com.mx.eval.demogpc.dto.UserResponse;

public interface UserService {

    UserResponse create(UserRequest request) throws Exception;

    List<UserResponse> findAll() throws Exception;

    void delete(String id) throws Exception;
}