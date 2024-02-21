package org.grida.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileImageService {

    private final ProfileImageRepository profileImageRepository;

    public List<ProfileImage> readImageHistory(String email) {
        return profileImageRepository.findAllImagesByUserEmail(email);
    }
}
