package com.hhplus.lecture.repository.orm;

import com.hhplus.lecture.domain.entity.LectureDetailEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LectureDetailJpaRepository extends JpaRepository<LectureDetailEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ld FROM LectureDetailEntity ld WHERE ld.lectureDetailId = :lectureDetailId")
    LectureDetailEntity findLectureDetailIdWithPessimisticLock(@Param("lectureDetailId") long lectureDetailId);

    Optional<LectureDetailEntity> findByLectureDetailId(long lectureDetailId);
}
