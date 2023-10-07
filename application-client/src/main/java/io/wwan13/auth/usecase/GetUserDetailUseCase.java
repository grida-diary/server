package io.wwan13.auth.usecase;

import io.wwan13.annotation.UseCase;
import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetUserDetailUseCase implements UserDetailsService {

    private final MemberQueryService memberQueryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberQueryService.findByEmail(username);
        return createUser(member);
    }

    private User createUser(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthorityName());
        List<GrantedAuthority> grantedAuthorities = Arrays.asList(grantedAuthority);

        return new User(member.getEmailValue(), member.getPasswordValue(), grantedAuthorities);
    }
}
