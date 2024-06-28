package com.hhplus.lecture.dto;

import com.hhplus.lecture.domain.Lecture;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
public class LectureResponseDto {

    private long lectureId;
    private String title;
    public static List<LectureResponseDto> from(List<Lecture> lectures) {
        return lectures.stream()
                .map(lecture -> LectureResponseDto.builder()
                        .lectureId(lecture.getLectureId())
                        .title(lecture.getTitle())
                        .build())
                .collect(Collectors.toList());
    }


}
