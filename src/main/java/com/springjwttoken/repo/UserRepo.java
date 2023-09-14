package com.springjwttoken.repo;

import com.springjwttoken.models.Role;
import com.springjwttoken.models.UserModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<UserModels , Integer> {
     Optional<UserModels> findByUsername (String username ) ;
}
