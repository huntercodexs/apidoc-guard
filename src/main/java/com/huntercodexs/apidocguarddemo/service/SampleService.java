package com.huntercodexs.apidocguarddemo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public ResponseEntity<?> admin() {
        return ResponseEntity.status(HttpStatus.OK).body("DASHBOARD ADMIN");
    }

    public ResponseEntity<?> users() {
        return ResponseEntity.status(HttpStatus.OK).body("DASHBOARD USERS");
    }

}
