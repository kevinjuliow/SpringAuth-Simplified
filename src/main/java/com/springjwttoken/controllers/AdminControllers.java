package com.springjwttoken.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminControllers {
    @GetMapping()
    public ResponseEntity<?> adminAccess (){
        return new ResponseEntity<>("admin Access" , HttpStatus.OK);
    }
}
