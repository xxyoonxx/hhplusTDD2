package com.hhplus.lecture.repository.orm;

import com.hhplus.lecture.domain.entity.LectureRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRegistrationJpaRepository extends JpaRepository<LectureRegistrationEntity, Long> {

}
