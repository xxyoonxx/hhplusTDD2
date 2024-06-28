package com.hhplus.lecture.dto;

import com.hhplus.lecture.domain.LectureDetail;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
public class LectureDetailResponseDto {

    private long lectureDetailId;
    private int currentCnt;
    private int capacity;

    public static LectureDetailResponseDto from(LectureDetail lectureDetail){
        return LectureDetailResponseDto.builder()
                .lectureDetailId(lectureDetail.getLectureDetailId())
                .currentCnt(lectureDetail.getCurrentCnt())
                .capacity(lectureDetail.getCapacity())
                .build();
    }

}
