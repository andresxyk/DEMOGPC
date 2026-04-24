package com.mx.eval.demogpc.model;

import com.google.cloud.Timestamp;

import lombok.Data;

@Data
public class Users {
     private String id;
    private String name;
    private String email;
    private String passwordHash;
    private String role;
    private Boolean active;
    private Timestamp createdAt;
}
