package com.springjwttoken.controllers;

import com.springjwttoken.dtos.UserDtos;
import com.springjwttoken.dtos.UserDtosToken;
import com.springjwttoken.exceptions.CustomExceptions;
import com.springjwttoken.repo.UserRepo;
import com.springjwttoken.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllers {
    @Autowired
    AuthServices authServices ;
    @Autowired
    UserRepo userRepo ;
    @Autowired
    PasswordEncoder passwordEncoder ;

    @PostMapping()
    public ResponseEntity<?> createUser (@RequestBody UserDtos body ){
        try{
          authServices.registerUser(body.getUsername() , body.getPassword());
          return new ResponseEntity<>("Success Created User \n User : "+body.getUsername() +
                  "\n Pass : "+body.getPassword(),  HttpStatus.OK) ;
        }
        catch (CustomExceptions err){
            return new ResponseEntity<>("error : "+err.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> loginUser (@RequestBody UserDtos body){
        UserDtosToken user =  authServices.loginUser(body.getUsername() , body.getPassword());
        if (user.getUsername() == null){
            return new ResponseEntity<>("User Not Found" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Username : "+user.getUsername()+",\nTokens : "+user.getToken() , HttpStatus.OK);
    }
}
