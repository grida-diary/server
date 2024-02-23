package org.grida.domain.user;

import lombok.RequiredArgsConstructor;
import org.grida.datetime.DateTimePicker;
import org.grida.exception.DomainException;
import org.grida.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.grida.exception.DomainErrorCode.ALREADY_ACTIVATE_IMAGE;
import static org.grida.exception.DomainErrorCode.IMAGE_ACCESS_DENIED;

@Service
@RequiredArgsConstructor
public class ProfileImageService {

    private final ProfileImageRepository profileImageRepository;
    private final DateTimePicker dateTimePicker;
    private final TransactionHandler transactionHandler;

    public String readActivateImagePath(String userEmail) {
        return profileImageRepository.findActivateImagePathByUserEmail(userEmail);
    }

    public List<ProfileImage> readImageHistory(String userEmail) {
        return profileImageRepository.findAllImagesByUserEmail(userEmail);
    }
    
    public long changeActivateImage(String userEmail, long targetImageId) {
        ProfileImage targetImage = profileImageRepository.findById(targetImageId);
        validateTargetImageIsDeactivate(targetImage);
        validateIsOwnImage(targetImage, userEmail);

        return transactionHandler.execute(() -> {
            profileImageRepository.deactivateImage(userEmail, dateTimePicker.now());
            profileImageRepository.activateImage(targetImageId, dateTimePicker.now());
            return targetImageId;
        });
    }

    private void validateTargetImageIsDeactivate(ProfileImage targetImage) {
        if (targetImage.imageDetail().isActivate()) {
            throw new DomainException(ALREADY_ACTIVATE_IMAGE);
        }
    }

    private void validateIsOwnImage(ProfileImage targetImage, String userEmail) {
        if (!targetImage.ownerEmail().equals(userEmail)) {
             throw new DomainException(IMAGE_ACCESS_DENIED);
        }
    }
}
