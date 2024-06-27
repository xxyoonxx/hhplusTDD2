package com.hhplus.lecture.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="lecture_detail")
public class LectureDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureDetailId;
    private long lectureId;
    private int currentCnt;
    private int capacity;
    private LocalDateTime startDate;

}
