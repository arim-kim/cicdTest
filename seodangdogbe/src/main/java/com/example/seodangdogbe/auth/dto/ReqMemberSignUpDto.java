package com.example.seodangdogbe.auth.dto;

import com.example.seodangdogbe.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqMemberSignUpDto {
    private String memberId;
    private String password;
    private String nickname;
    private String role;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .nickname(nickname)
                .password(password)
                .role("Member")
                .build();
    }
}
