package com.hhplus.lecture.repository.orm;

import com.hhplus.lecture.domain.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

}
