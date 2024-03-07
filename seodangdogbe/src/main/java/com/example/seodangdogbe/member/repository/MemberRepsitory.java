package com.example.seodangdogbe.member.repository;

import com.example.seodangdogbe.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MemberRepsitory extends JpaRepository<Member, Long> , CustomMemberRepository{


}
