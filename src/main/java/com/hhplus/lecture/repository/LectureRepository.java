package com.hhplus.lecture.repository;

import com.hhplus.lecture.domain.Lecture;

import java.util.List;

public interface LectureRepository {
    List<Lecture> findAppliedLectures(long userId);
    List<Lecture> findAllById(List<Long> lectureIds);

}