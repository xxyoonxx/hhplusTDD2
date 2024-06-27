package com.hhplus.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureDetail {

    private long lectureDetailId;
    private int currentCnt;
    private int capacity;

}
