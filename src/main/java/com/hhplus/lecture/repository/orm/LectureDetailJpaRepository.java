package com.hhplus.lecture.repository.orm;

import com.hhplus.lecture.domain.entity.LectureDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureDetailJpaRepository extends JpaRepository<LectureDetailEntity, Long> {
    Optional<LectureDetailEntity> findByLectureDetailId(long lectureDetailId);
}
