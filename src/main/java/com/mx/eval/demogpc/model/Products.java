package com.mx.eval.demogpc.model;

import com.google.cloud.Timestamp;

import lombok.Data;

@Data
public class Products {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Timestamp createdAt;
}
