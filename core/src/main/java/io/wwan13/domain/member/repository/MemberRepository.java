package io.wwan13.domain.member.repository;

import io.wwan13.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailEmail(String email);
    Boolean existsByEmail(String email);
}
