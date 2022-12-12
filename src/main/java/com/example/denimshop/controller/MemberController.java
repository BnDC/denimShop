package com.example.denimshop.controller;

import com.example.denimshop.dto.LoginResponse;
import com.example.denimshop.dto.MemberInputDto;
import com.example.denimshop.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.OptionalLong;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Long> login(
            @Validated @RequestBody MemberInputDto memberInputDto,
            HttpServletResponse response
    ) {
        LoginResponse loginResponse;

        loginResponse = memberService.login(memberInputDto);
        Cookie cookie = new Cookie("token", loginResponse.getToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(loginResponse.getMemberId(), HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Long> check(@CookieValue(value = "token", required = false) String token) {
        OptionalLong optionalId = memberService.loginCheck(token);
        if (optionalId.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(optionalId.getAsLong(), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(
            @Validated @RequestBody MemberInputDto memberInputDto,
            HttpServletResponse response
    ) {
        LoginResponse loginResponse;

        loginResponse = memberService.signup(memberInputDto);
        Cookie cookie = new Cookie("token", loginResponse.getToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(loginResponse.getMemberId(), HttpStatus.OK);
    }
}
