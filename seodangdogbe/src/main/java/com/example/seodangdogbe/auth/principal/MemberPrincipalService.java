package com.example.seodangdogbe.auth.principal;

import com.example.seodangdogbe.member.entity.Member;
import com.example.seodangdogbe.member.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberPrincipalService implements UserDetailsService {

    private final MemberRepsitory memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        log.info("*** login loadUserByUsername *** ");
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        return new MemberPrincipal(member);
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    // 이걸로 빼면 왜 안됨 ㅎㅎ ? 개빡친다
//    private UserDetails createUserDetails(Member member) {
//        log.info("*** login createUserDetails *** ");
//        Member createMember = Member.builder()
//                .memberId(member.getMemberId())
//                .password(passwordEncoder.encode(member.getPassword()))
//                .role("Member")
//                .build();
//
//        System.out.println(member);
//        return new MemberPrincipal(createMember);
//    }

}
