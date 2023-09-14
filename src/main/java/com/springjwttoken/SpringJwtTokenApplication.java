package com.springjwttoken;

import com.springjwttoken.models.Role;
import com.springjwttoken.models.UserModels;
import com.springjwttoken.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringJwtTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtTokenApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepo userRepo , PasswordEncoder passwordEncoder){
        return args -> {
            Role adminRole = Role.ADMIN ;
            UserModels adminUser = new UserModels("admin" , passwordEncoder.encode("admin") ,
                    adminRole );
            userRepo.save(adminUser) ;
        };
    }

}
