package com.springjwttoken.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

public enum Role {
    @Enumerated
    ADMIN , USER ;
}
