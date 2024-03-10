package org.grida.entity.diary;

import org.grida.datetime.DateTimeRange;
import org.grida.domain.diary.Diary;
import org.grida.domain.diary.DiaryContents;
import org.grida.domain.diary.DiaryCursor;
import org.grida.domain.diary.DiaryRepository;
import org.grida.exception.DomainRdsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.grida.exception.DomainRdsErrorCode.DIARY_NOT_FOUND;

@Repository
@Transactional(readOnly = true)
public class DiaryEntityRepository implements DiaryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public long save(
            String userEmail,
            DiaryContents contents,
            int imageRefreshChance,
            LocalDateTime lastActionAt
    ) {
        DiaryEntity entity =
                DiaryMapper.toDiaryEntity(userEmail, contents, imageRefreshChance, lastActionAt);
        entityManager.persist(entity);

        return entity.getId();
    }

    @Override
    public Diary findById(long id) {
        return DiaryMapper.toDiary(findEntityById(id));
    }

    @Override
    public List<Diary> findAllByUserEmailAndTargetDateBetween(String userEmail, DateTimeRange range) {
        return entityManager.createQuery(
                        "select d from DiaryEntity d " +
                                "where d.userEmail = :userEmail " +
                                "and d.targetDate between :start and :end " +
                                "order by targetDate asc ",
                        DiaryEntity.class
                )
                .setParameter("userEmail", userEmail)
                .setParameter("start", range.start())
                .setParameter("end", range.end())
                .getResultStream()
                .map(DiaryMapper::toDiary)
                .toList();
    }

    @Override
    public List<Diary> findAllByUseEmailAndDiaryCursor(String userEmail, DiaryCursor cursor) {
        return entityManager.createQuery(
                        "select d from DiaryEntity d " +
                                "where d.userEmail = :userEmail " +
                                "and d.targetDate < :lastTargetDate " +
                                "order by d.targetDate desc ",
                        DiaryEntity.class
                )
                .setParameter("userEmail", userEmail)
                .setParameter("lastTargetDate", cursor.lastTargetDate())
                .setMaxResults(cursor.size())
                .getResultStream()
                .map(DiaryMapper::toDiary)
                .toList();
    }

    @Override
    @Transactional
    public void delete(long id) {
        entityManager.remove(findEntityById(id));
    }

    private DiaryEntity findEntityById(long id) {
        return entityManager.createQuery(
                        "select d from DiaryEntity d " +
                                "where d.id = :id ",
                        DiaryEntity.class
                )
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(DIARY_NOT_FOUND));
    }
}
