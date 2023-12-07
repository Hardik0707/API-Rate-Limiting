package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    String message;

    public ApiResponse(String message) {
        this.message = message;
    }
}
