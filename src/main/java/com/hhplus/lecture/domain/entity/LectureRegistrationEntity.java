package com.hhplus.lecture.domain.entity;

import com.hhplus.lecture.domain.LectureRegistration;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Entity
@Table(name="lecture_registration")
@NoArgsConstructor
public class LectureRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureRegistrationId;
    private long lectureDetailId;
    private long userId;

    public LectureRegistrationEntity(LectureRegistration lectureRegistration) {
        BeanUtils.copyProperties(lectureRegistration, this);
    }

}
