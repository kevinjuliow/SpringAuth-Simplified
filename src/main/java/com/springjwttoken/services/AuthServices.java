package com.springjwttoken.services;

import com.springjwttoken.dtos.UserDtosToken;
import com.springjwttoken.exceptions.CustomExceptions;
import com.springjwttoken.models.Role;
import com.springjwttoken.models.UserModels;
import com.springjwttoken.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthServices {
    @Autowired
    private UserRepo userRepo ;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private AuthenticationManager authenticationManager ;

    public UserModels registerUser (String username , String password) throws CustomExceptions {
        if (userRepo.findByUsername(username).isPresent()) throw new CustomExceptions("User Already Exist");
        Role userRole = Role.USER ;
        UserModels newUser = new UserModels(username , passwordEncoder.encode(password) , userRole) ;
        userRepo.save(newUser) ;
        return newUser ;
    }

    public UserDtosToken loginUser (String username ,String password) {
        try{
            Authentication auth =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username , password));

            String token = jwtGenerator(auth) ;
            return new UserDtosToken(userRepo.findByUsername(username).get().getUsername() , password , token) ;
        }
        catch (AuthenticationException err ){
            return new UserDtosToken(null , null , null );
        }
    }

    //Token Generator
    @Autowired private JwtEncoder jwtEncoder ;
    @Autowired private JwtDecoder jwtDecoder ;

    public String jwtGenerator (Authentication auth) {
        Instant now = Instant.now() ;
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" ")) ;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles" , scope)
                .build() ;
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue() ;
    }


}
