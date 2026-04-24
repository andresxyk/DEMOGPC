package com.mx.eval.demogpc.dto;

import com.google.cloud.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
     private String id;
    private String name;
    private String email;
    private String role;
    private Boolean active;
    private Timestamp createdAt;
}
