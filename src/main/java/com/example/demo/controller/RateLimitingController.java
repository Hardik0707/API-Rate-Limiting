package com.example.demo.controller;

import com.example.demo.config.RateLimitConfig;
import com.example.demo.model.ApiResponse;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitingController {

    @Autowired
    RateLimitConfig rateLimitConfig;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getInfo(@PathVariable("id") String id)
    {
        Bucket bucket = rateLimitConfig.resolveBucket(id);
        if(bucket.tryConsume(1))
        {
            return ResponseEntity.status(200).body(new ApiResponse("Success for user " + id));
        }else {
            return ResponseEntity.status(429).body(new ApiResponse("Rate limit exceeded for user " + id));
        }

    }
}
