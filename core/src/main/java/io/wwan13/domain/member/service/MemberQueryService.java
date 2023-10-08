package io.wwan13.domain.member.service;

import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.exception.MemberNotFountException;
import io.wwan13.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public Member findByEmail(String email) {
        return memberRepository.findByEmailEmail(email)
                .orElseThrow(() -> new MemberNotFountException());
    }

    public boolean existByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
