package com.hhplus.lecture.domain.entity;

import com.hhplus.lecture.domain.LectureDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="lecture_detail")
@NoArgsConstructor
public class LectureDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureDetailId;
    private long lectureId;
    private int currentCnt;
    private int capacity;
    private LocalDateTime startDate;
    @Version
    private Integer version;

    public LectureDetailEntity(LectureDetail lectureDetail) {
        BeanUtils.copyProperties(lectureDetail, this);
    }

    public LectureDetail toDomain(){
        LectureDetail lectureDetail = new LectureDetail();
        BeanUtils.copyProperties(this, lectureDetail);
        return lectureDetail;
    }

}
