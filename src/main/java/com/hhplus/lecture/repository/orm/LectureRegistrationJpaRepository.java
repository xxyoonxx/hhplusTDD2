package com.hhplus.lecture.repository.orm;

import com.hhplus.lecture.domain.entity.LectureRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRegistrationJpaRepository extends JpaRepository<LectureRegistrationEntity, Long> {
    List<LectureRegistrationEntity> findByUserId(long userId);
    boolean existsByLectureDetailIdAndUserId(long lectureDetailId, long userId);
}
