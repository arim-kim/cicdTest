package com.example.seodangdogbe.auth;

import com.example.seodangdogbe.auth.jwt.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWT> loginSuccess(@RequestBody ReqLoginDto reqLoginDto){
        log.info("*** 로그인 요청 ***");
        JWT jwt = authService.login(reqLoginDto);
        return ResponseEntity.ok(jwt);
    }
}
