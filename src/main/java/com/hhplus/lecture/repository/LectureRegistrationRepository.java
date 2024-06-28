package com.hhplus.lecture.repository;

import com.hhplus.lecture.domain.LectureRegistration;

public interface LectureRegistrationRepository {

    void applyLecture(LectureRegistration lectureRegistration);
    boolean isApplied(long lectureDeatilId, long userId);

}