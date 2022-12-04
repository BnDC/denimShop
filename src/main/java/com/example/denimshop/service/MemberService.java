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
import java.util.OptionalLong;

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

    public OptionalLong loginCheck(String token) {
        Optional<Claims> optionalClaims = jwtService.getClaims(token);
        if (optionalClaims.isEmpty()) {
            return OptionalLong.empty();
        }
        return OptionalLong.of(Long.parseLong(optionalClaims.get().get("id").toString()));
    }

    public LoginResponse signup(MemberInputDto memberInputDto) {
        if (memberRepository.existsByEmail(memberInputDto.getEmail())) {
            throw new DuplicatedEmailException();
        }

        Member member = memberRepository.save(Member.builder()
                .email(memberInputDto.getEmail())
                .password(memberInputDto.getPassword())
                .build());

        String token = jwtService.getToken("id", member.getMemberId());

        return new LoginResponse(member.getMemberId(), token);
    }
}
