package org.grida.domain.user;

import lombok.RequiredArgsConstructor;
import org.grida.datetime.DateTimePicker;
import org.grida.domain.core.ImageDetail;
import org.grida.exception.DomainException;
import org.grida.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.grida.exception.DomainErrorCode.CANNOT_REFRESH_PROFILE_IMAGE;
import static org.grida.exception.DomainErrorCode.PROFILE_IMAGE_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class UserWithImageService {

    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;
    private final DateTimePicker dateTimePicker;
    private final TransactionHandler transactionHandler;

    public String onboarding(String userEmail, UserAppearance appearance, String imagePath) {
        validateImageAlreadyExist(userEmail);

        ImageDetail imageDetail = new ImageDetail(imagePath, true);

        return transactionHandler.execute(() -> {
            profileImageRepository.save(userEmail, imageDetail, dateTimePicker.now());
            return userRepository.modifyAppearance(userEmail, appearance);
        });
    }

    private void validateImageAlreadyExist(String userEmail) {
        if (profileImageRepository.hasActivateImage(userEmail)) {
            throw new DomainException(PROFILE_IMAGE_ALREADY_EXIST);
        }
    }

    public String refreshProfileImage(String userEmail, UserAppearance appearance, String imagePath) {
        validateCanRefresh(userEmail);

        ImageDetail imageDetail = new ImageDetail(imagePath, true);

        return transactionHandler.execute(() -> {
            profileImageRepository.deactivateImage(userEmail, dateTimePicker.now());
            profileImageRepository.save(userEmail, imageDetail, dateTimePicker.now());
            return userRepository.modifyAppearance(userEmail, appearance);
        });
    }

    public boolean canRefreshProfileImage(String userEmail) {
        LocalDate nextRefreshableDate = calculateNextRefreshableDate(userEmail);
        return nextRefreshableDate.isAfter(dateTimePicker.now().toLocalDate());
    }

    public LocalDate calculateNextRefreshableDate(String userEmail) {
        LocalDate mostRecentCreatedDate =
                profileImageRepository.findMostRecentCreatedDateByUserEmail(userEmail);
        int refreshTerm = userRepository.findRoleByEmail(userEmail).getProfileImageRefreshTerm();

        return mostRecentCreatedDate.plus(refreshTerm, DAYS);
    }

    private void validateCanRefresh(String userEmail) {
        LocalDate nextRefreshableDate = calculateNextRefreshableDate(userEmail);

        if (nextRefreshableDate.isBefore(dateTimePicker.now().toLocalDate())) {
            throw new DomainException(
                    CANNOT_REFRESH_PROFILE_IMAGE,
                    nextRefreshableDate.getMonth(),
                    nextRefreshableDate.getDayOfMonth()
            );
        }
    }
}
