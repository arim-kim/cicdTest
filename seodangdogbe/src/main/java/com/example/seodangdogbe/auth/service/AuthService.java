package com.example.seodangdogbe.auth.service;


import com.example.seodangdogbe.auth.principal.MemberPrincipal;
import com.example.seodangdogbe.auth.dto.ReqMemberLoginDto;
import com.example.seodangdogbe.auth.dto.ReqMemberSignUpDto;
import com.example.seodangdogbe.jwt.JWT;
import com.example.seodangdogbe.jwt.JWTProvider;
import com.example.seodangdogbe.member.entity.Member;
import com.example.seodangdogbe.member.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final MemberRepsitory memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Long signUp(ReqMemberSignUpDto memberSignUpDto) {

        if (memberRepository.findByMemberId(memberSignUpDto.getMemberId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        Member member = memberSignUpDto.toEntity();
        member.encodePassword(passwordEncoder); // 비밀번호 해싱
        System.out.println(member.getPassword());
        member = memberRepository.save(member); // 저장

        return member.getMemberSeq();
    }

    public JWT login(ReqMemberLoginDto reqMemberLoginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(reqMemberLoginDto.getMemberId(), reqMemberLoginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JWT jwt = jwtProvider.generateToken(authentication);
        System.out.println(jwt);
        // 최종 토큰 생성
        return jwt;
    }

    // 회원 seq 가져오기
    // 로그인한 유저가 요청할 때 해당 토큰에 userId값을 넣어놨으므로 해당 userId값으로 member 테이블 조회 -> seq 가져오는 로직
    // 로그인시에 토큰에 저장하게 하는 방법도 고려해보기 -> 한번만 select 하면 되니까..?
    public Long getMemberSeq() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName();
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        return member.getMemberSeq();
    }
}
