package io.wwan13.domain.profileimage.service;

import io.wwan13.domain.member.entity.Member;
import io.wwan13.domain.profileimage.entity.ProfileImage;
import io.wwan13.domain.profileimage.exception.ProfileImageNotFoundException;
import io.wwan13.domain.profileimage.repository.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileImageQueryService {

    private final ProfileImageRepository profileImageRepository;

    public ProfileImage findByMember(Member member) {
        return profileImageRepository.findByMember(member)
                .orElseThrow(() -> new ProfileImageNotFoundException());
    }

}
