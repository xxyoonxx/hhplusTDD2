package com.hhplus.lecture.service;

import com.hhplus.lecture.domain.Lecture;
import com.hhplus.lecture.domain.LectureDetail;
import com.hhplus.lecture.dto.LectureRequestDto;

import java.util.List;

public interface LectureService {

    LectureDetail applyLecture(LectureRequestDto dto);
    List<Lecture> getLectureList();
    List<Lecture> getAppliedLectureList(long userId);

}
