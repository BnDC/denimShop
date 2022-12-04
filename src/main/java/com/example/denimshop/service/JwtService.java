package com.example.denimshop.service;

public interface JwtService {
    String getToken(String key, Object value);
}
