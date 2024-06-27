package com.hhplus.lecture.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="lecture")
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureId;
    private String title;

}
