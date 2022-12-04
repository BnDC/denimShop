package com.example.denimshop.controller;

import com.example.denimshop.dto.MemberInputDto;
import com.example.denimshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public long login(@RequestBody MemberInputDto memberInputDto) {
        return memberService.login(memberInputDto).getMemberId();
    }

}
