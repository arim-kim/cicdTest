package com.example.seodangdogbe.auth.controller;

import com.example.seodangdogbe.auth.dto.JWTDto;
import com.example.seodangdogbe.auth.service.AuthService;
import com.example.seodangdogbe.common.TestMessageDto;
import com.example.seodangdogbe.jwt.JWT;
import com.example.seodangdogbe.auth.dto.ReqMemberLoginDto;
import com.example.seodangdogbe.auth.dto.ReqMemberSignUpDto;
import com.example.seodangdogbe.member.entity.Member;
import com.example.seodangdogbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<Long> join(@RequestBody ReqMemberSignUpDto memberSignUpDto) {
        log.info("*** join 요청 *** ");
        Long id =  authService.signUp(memberSignUpDto);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTDto> login(@RequestBody ReqMemberLoginDto memberLoginDto){
        log.info("*** login 요청 *** ");
        JWT token = authService.login(memberLoginDto);

        JWTDto responseDto =
        JWTDto.builder()
                .accessToken(token.getGrantType() + " " + token.getAccessToken())
                .refreshToken(token.getGrantType() + " " + token.getRefreshToken())
                .build();
        return ResponseEntity.ok(responseDto);
    }

//    @GetMapping("/test") // login 후 test용도
//    public ResponseEntity<TestMessageDto> test(){
//        log.info("*** test 요청 *** ");
//        System.out.println(authService.getMemberSeq());
//        return ResponseEntity.ok(new TestMessageDto("ok"));
//    }
}
