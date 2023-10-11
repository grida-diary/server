package io.wwan13.domain.member.service;

import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String save(Member member) {
        Member createdMember = memberRepository.save(member);
        return createdMember.getEmailValue();
    }
}
