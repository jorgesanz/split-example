package com.example.split.controller;

import com.example.split.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    @Autowired
    private SayHelloService sayHelloService;

    @GetMapping(path = "/say-hello/{user}")
    public ResponseEntity<String> features(@PathVariable String user) {
        try {
            return ResponseEntity.ok(sayHelloService.getMessage(user));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
