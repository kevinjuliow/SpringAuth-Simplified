package com.springjwttoken.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
@Component
@Data
public class KeyProperties {
    private RSAPublicKey publicKey ;
    private RSAPrivateKey privateKey ;
    
    public KeyProperties() {
        KeyPair keyPair  = keyGenerator();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }
    
    public static KeyPair keyGenerator (){
        KeyPair keyPair;
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); //JWT Algorithms used
            keyPairGenerator.initialize(2048); //2048 bits
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception err){
            throw new IllegalStateException();
        }
        return keyPair;
    }

}
