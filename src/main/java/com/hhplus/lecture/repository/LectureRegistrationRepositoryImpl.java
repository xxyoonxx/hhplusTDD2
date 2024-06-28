package com.hhplus.lecture.repository;

import com.hhplus.lecture.domain.LectureRegistration;
import com.hhplus.lecture.domain.entity.LectureRegistrationEntity;
import com.hhplus.lecture.repository.orm.LectureRegistrationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureRegistrationRepositoryImpl implements LectureRegistrationRepository {

    private final LectureRegistrationJpaRepository lectureRegistrationJpaRepository;


    @Override
    public void applyLecture(LectureRegistration lectureRegistration) {
        LectureRegistrationEntity lectureRegistrationEntity = new LectureRegistrationEntity(lectureRegistration);
        lectureRegistrationJpaRepository.save(lectureRegistrationEntity);
    }

    @Override
    public boolean isApplied(long lectureDetailId, long userId) {
        return lectureRegistrationJpaRepository.existsByLectureDetailIdAndUserId(lectureDetailId, userId);
    }
}