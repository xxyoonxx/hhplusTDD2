package com.hhplus.lecture.controller;

import com.hhplus.lecture.dto.LectureRequestDto;
import com.hhplus.lecture.dto.LectureDetailResponseDto;
import com.hhplus.lecture.dto.LectureResponseDto;
import com.hhplus.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    /**
     * 특강 신청
     * @param dto
     * @return
     */
    @PostMapping("/apply")
    public LectureDetailResponseDto apply(@RequestBody LectureRequestDto dto) {
        return LectureDetailResponseDto.from(lectureService.applyLecture(dto));
    }

    /**
     * 강의 목록 조회
     * @return
     */
    @GetMapping("/")
    public List<LectureResponseDto> getlectureList() {
        return LectureResponseDto.from(lectureService.getLectureList());
    }


    /**
     * 특강 신청 완료 여부 조회
     * @param userId
     * @return
     */
    @GetMapping("/application/{userId}")
    public List<LectureResponseDto> getAppliedLectureList(@PathVariable long userId) {
        return LectureResponseDto.from(lectureService.getAppliedLectureList(userId));
    }

}
