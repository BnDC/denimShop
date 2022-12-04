package com.example.denimshop.service;

import com.example.denimshop.dto.LoginResponse;
import com.example.denimshop.dto.MemberInputDto;
import com.example.denimshop.entity.Member;
import com.example.denimshop.exception.DuplicatedEmailException;
import com.example.denimshop.exception.UserNotFoundException;
import com.example.denimshop.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public LoginResponse login(MemberInputDto memberInputDto) {
        Optional<Member> optionalMember = memberRepository.findByEmailAndPassword(memberInputDto.getEmail(), memberInputDto.getPassword());
        if (optionalMember.isEmpty()) {
            throw new UserNotFoundException();
        }
        Member member = optionalMember.get();
        String token = jwtService.getToken("id", member.getMemberId());

        return new LoginResponse(member.getMemberId(), token);
    }

    public Member login(MemberInputDto memberInputDto) {
        Optional<Member> byEmailAndPassword = memberRepository.findByEmailAndPassword(memberInputDto.getEmail(), memberInputDto.getPassword());
        if (byEmailAndPassword.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return byEmailAndPassword.get();
    }

}
