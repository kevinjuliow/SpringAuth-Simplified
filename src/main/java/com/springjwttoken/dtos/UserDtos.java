package com.springjwttoken.dtos;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDtos {
    private String username ;
    private String password ;
}
