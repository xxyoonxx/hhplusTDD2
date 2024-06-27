package com.hhplus.lecture.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="lecture_registration")
public class LectureRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureRegistrationId;
    private long lectureId;
    private long userId;

}
