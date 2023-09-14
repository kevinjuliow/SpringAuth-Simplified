package com.springjwttoken.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDtosToken {
    private String username ;
    private String password ;
    private String token  ;
}
