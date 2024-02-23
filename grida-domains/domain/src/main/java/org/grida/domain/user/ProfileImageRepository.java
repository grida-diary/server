package org.grida.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProfileImageRepository {
    long save(String userEmail, ImageDetail imageDetail, LocalDateTime lastActionAt);
    ProfileImage findById(long userEmail);
    String findActivateImagePathByUserEmail(String userEmail);
    List<ProfileImage> findAllImagesByUserEmail(String userEmail);
    LocalDate findMostRecentCreatedDateByUserEmail(String userEmail);
    boolean hasActivateImage(String userEmail);
    void deactivateImage(String userEmail, LocalDateTime lastActionAt);
    void activateImage(long id, LocalDateTime lastActionAt);
}
