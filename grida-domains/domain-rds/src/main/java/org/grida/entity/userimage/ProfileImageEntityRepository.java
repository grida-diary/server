package org.grida.entity.userimage;

import lombok.RequiredArgsConstructor;
import org.grida.domain.user.ImageDetail;
import org.grida.domain.user.ProfileImage;
import org.grida.domain.user.ProfileImageRepository;
import org.grida.exception.DomainRdsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.grida.exception.DomainRdsErrorCode.PROFILE_IMAGE_NOT_FOUND;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileImageEntityRepository implements ProfileImageRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public long save(
            String userEmail,
            ImageDetail imageDetail,
            LocalDateTime lastActionAt
    ) {

        ProfileImageEntity profileImageEntity =
                ProfileImageMapper.toProfileImageEntity(userEmail, imageDetail, lastActionAt);
        entityManager.persist(profileImageEntity);

        return profileImageEntity.getId();
    }

    @Override
    public ProfileImage findById(long userEmail) {
        ProfileImageEntity profileImageEntity = findImageEntityById(userEmail);

        return ProfileImageMapper.toProfileImage(profileImageEntity);
    }

    @Override
    public String findActivateImagePathByUserEmail(String userEmail) {
        return entityManager.createQuery(
                        "select pi.imagePath from ProfileImageEntity pi " +
                                "where pi.userEmail = :userEmail " +
                                "and pi.isActivate = true ",
                        String.class
                )
                .setParameter("userEmail", userEmail)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(PROFILE_IMAGE_NOT_FOUND));
    }

    @Override
    public List<ProfileImage> findAllImagesByUserEmail(String userEmail) {
        return entityManager.createQuery(
                        "select pi from ProfileImageEntity pi " +
                                "where pi.userEmail = :userEmail ",
                        ProfileImageEntity.class
                )
                .setParameter("userEmail", userEmail)
                .getResultStream()
                .map(ProfileImageMapper::toProfileImage)
                .toList();
    }

    @Override
    public LocalDate findMostRecentCreatedDateByUserEmail(String userEmail) {
        return entityManager.createQuery(
                        "select pi.createdAt from ProfileImageEntity pi " +
                                "where pi.userEmail = :userEmail " +
                                "order by pi.createdAt desc ",
                        LocalDateTime.class
                )
                .setParameter("userEmail", userEmail)
                .setMaxResults(1)
                .getResultStream()
                .map(LocalDateTime::toLocalDate)
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(PROFILE_IMAGE_NOT_FOUND));
    }

    @Override
    public boolean hasActivateImage(String userEmail) {
        return !entityManager.createQuery(
                        "select pi.isActivate from ProfileImageEntity pi " +
                                "where pi.userEmail = :userEmail ",
                        Boolean.class
                )
                .setParameter("userEmail", userEmail)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    @Transactional
    public void deactivateImage(String userEmail, LocalDateTime lastActionAt) {
        ProfileImageEntity profileImageEntity = findActivateImageEntityByUserEmail(userEmail);
        profileImageEntity.deactivate(lastActionAt);
    }

    @Override
    public void activateImage(long id, LocalDateTime lastActionAt) {
        ProfileImageEntity profileImageEntity = findImageEntityById(id);
        profileImageEntity.activate(lastActionAt);
    }

    private ProfileImageEntity findActivateImageEntityByUserEmail(String userEmail) {
        return entityManager.createQuery(
                        "select pi from ProfileImageEntity pi " +
                                "where pi.userEmail = :userEmail " +
                                "and pi.isActivate = true ",
                        ProfileImageEntity.class
                )
                .setParameter("userEmail", userEmail)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(PROFILE_IMAGE_NOT_FOUND));
    }

    private ProfileImageEntity findImageEntityById(long id) {

        return entityManager.createQuery(
                        "select pi from ProfileImageEntity pi " +
                                "where pi.id = :id ",
                        ProfileImageEntity.class
                )
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(PROFILE_IMAGE_NOT_FOUND));
    }
}
