package com.example.denimshop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {
    private final long memberId;
    private final String token;
}
