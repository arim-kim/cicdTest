package com.example.seodangdogbe.member.repository;

import com.example.seodangdogbe.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepsitory extends JpaRepository<Member, Long> , CustomMemberRepository{

    Optional<Member> findByMemberId(String memberId);

}
