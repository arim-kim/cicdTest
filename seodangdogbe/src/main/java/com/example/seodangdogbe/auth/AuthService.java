package com.example.seodangdogbe.auth;

import com.example.seodangdogbe.auth.jwt.JWTProvider;
import com.example.seodangdogbe.member.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JWTProvider jwtProvider;
    private final MemberRepsitory memberRepsitory;


}
