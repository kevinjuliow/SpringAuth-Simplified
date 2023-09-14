package com.springjwttoken.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserControllers {

    @GetMapping()
    public ResponseEntity<?> userAccess (){
        return new ResponseEntity<>("User Access" , HttpStatus.OK);
    }
}
