package com.example.denimshop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void existsByMemberId() {
        boolean b = memberRepository.existsByEmail("example@naver.com");
        boolean c = memberRepository.existsByEmail("aaaaaaa");

        System.out.println(b);
        System.out.println(c);
    }
}