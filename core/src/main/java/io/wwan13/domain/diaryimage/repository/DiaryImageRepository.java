package io.wwan13.domain.diaryimage.repository;

import io.wwan13.domain.diaryimage.entity.DiaryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryImageRepository extends JpaRepository<DiaryImage, Long> {
    Optional<DiaryImage> findByDiaryId(Long diaryId);
}
