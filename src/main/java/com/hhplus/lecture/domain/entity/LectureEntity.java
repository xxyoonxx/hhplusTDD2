package com.hhplus.lecture.domain.entity;

import com.hhplus.lecture.domain.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Entity
@Table(name="lecture")
@NoArgsConstructor
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureId;
    private String title;

    public Lecture toDomain(){
        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(this, lecture);
        return lecture;
    }

}
