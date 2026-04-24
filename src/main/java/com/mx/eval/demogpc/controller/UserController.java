package com.mx.eval.demogpc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.eval.demogpc.dto.UserRequest;
import com.mx.eval.demogpc.dto.UserResponse;
import com.mx.eval.demogpc.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) throws Exception {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("Usuario eliminado");
    }
}