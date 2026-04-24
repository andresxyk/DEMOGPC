package com.mx.eval.demogpc.service.impl;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.mx.eval.demogpc.dto.UserRequest;
import com.mx.eval.demogpc.dto.UserResponse;
import com.mx.eval.demogpc.model.Users;
import com.mx.eval.demogpc.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final Firestore firestore;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(Firestore firestore, PasswordEncoder passwordEncoder) {
        this.firestore = firestore;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse create(UserRequest request) throws Exception {
        String id = UUID.randomUUID().toString();

        Users user = new Users();
        user.setId(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        user.setActive(request.getActive() != null ? request.getActive() : true);
        user.setCreatedAt(Timestamp.now());

        firestore.collection("Users").document(id).set(user).get();

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getActive(),
                user.getCreatedAt()
        );
    }

    @Override
    public List<UserResponse> findAll() throws Exception {
        List<QueryDocumentSnapshot> docs = firestore.collection("Users")
                .get()
                .get()
                .getDocuments();

        List<UserResponse> result = new ArrayList<>();

        for (QueryDocumentSnapshot doc : docs) {
            Users user = doc.toObject(Users.class);
            user.setId(doc.getId());

            result.add(new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole(),
                    user.getActive(),
                    user.getCreatedAt()
            ));
        }

        return result;
    }

    @Override
    public void delete(String id) throws Exception {
        firestore.collection("Users").document(id).delete().get();
    }
}