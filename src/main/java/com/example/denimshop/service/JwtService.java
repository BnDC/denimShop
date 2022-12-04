package com.example.denimshop.service;

import io.jsonwebtoken.Claims;

import java.util.Optional;

public interface JwtService {
    String getToken(String key, Object value);

    Optional<Claims> getClaims(String token);
}
