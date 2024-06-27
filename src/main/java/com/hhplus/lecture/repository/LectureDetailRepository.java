package com.hhplus.lecture.repository;


import com.hhplus.lecture.domain.LectureDetail;

import java.util.List;

public interface LectureDetailRepository {

    LectureDetail getLectureDetailDomain(long lectureDetailId);
    LectureDetail detailUpdate(LectureDetail lectureDetail);
    List<Long> findAvaliableLectures();

}