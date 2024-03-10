package org.grida.entity.diaryimage;

import org.grida.domain.core.ImageDetail;
import org.grida.domain.diary.DiaryImage;
import org.grida.domain.diary.DiaryImageRepository;
import org.grida.exception.DomainRdsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.grida.exception.DomainRdsErrorCode.DIARY_IMAGE_NOT_FOUND;

@Repository
@Transactional(readOnly = true)
public class DiaryImageEntityRepository implements DiaryImageRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public long save(long diaryId, ImageDetail imageDetail, LocalDateTime lastActionAt) {
        DiaryImageEntity entity = DiaryImageMapper.toDiaryImageEntity(diaryId, imageDetail, lastActionAt);
        entityManager.persist(entity);

        return entity.getId();
    }

    @Override
    public DiaryImage findActivateImageByDiaryId(long diaryId) {
        return DiaryImageMapper.toDiaryImage(findActivateImageEntityByDiaryId(diaryId));
    }

    @Override
    public List<DiaryImage> findAllByDiaryId(long diaryId) {
        return findAllEntitiesStreamByDiaryId(diaryId)
                .map(DiaryImageMapper::toDiaryImage)
                .toList();
    }

    @Override
    @Transactional
    public void deactivateImage(long diaryId) {
        DiaryImageEntity entity = findActivateImageEntityByDiaryId(diaryId);
        entity.deactivate();
    }

    @Override
    @Transactional
    public void deleteAllByDiaryId(long diaryId) {
        findAllEntitiesStreamByDiaryId(diaryId)
                .forEach(entityManager::remove);
    }

    private DiaryImageEntity findActivateImageEntityByDiaryId(long diaryId) {
        return entityManager.createQuery(
                        "select di from DiaryImageEntity di " +
                                "where di.diaryId = : diaryId " +
                                "and di.isActivate = true",
                        DiaryImageEntity.class
                )
                .setParameter("diaryId", diaryId)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(DIARY_IMAGE_NOT_FOUND));
    }

    private Stream<DiaryImageEntity> findAllEntitiesStreamByDiaryId(long diaryId) {
        return entityManager.createQuery(
                        "select di from DiaryImageEntity di " +
                                "where di.diaryId = : diaryId ",
                        DiaryImageEntity.class
                )
                .setParameter("diaryId", diaryId)
                .getResultStream();
    }
}
