package com.hhplus.lecture;

import com.hhplus.lecture.domain.LectureDetail;
import com.hhplus.lecture.dto.LectureRequestDto;
import com.hhplus.lecture.error.ErrorCode;
import com.hhplus.lecture.error.LectureException;
import com.hhplus.lecture.repository.LectureDetailRepository;
import com.hhplus.lecture.repository.LectureRegistrationRepository;
import com.hhplus.lecture.service.LectureServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LectureExceptionTest {

    @Mock
    private LectureDetailRepository lectureDetailRepository;

    @Mock
    private LectureRegistrationRepository lectureRegistrationRepository;

    @InjectMocks
    private LectureServiceImpl lectureService;

    @Test
    public void testApplyLecture_CapacityExceeded() {
        LectureRequestDto requestDto = new LectureRequestDto(4L, 2L);
        LectureDetail lectureDetail = new LectureDetail(4L, 30, 30,1);

        when(lectureDetailRepository.getLectureDetailDomain(requestDto.getLectureDetailId())).thenReturn(lectureDetail);

        LectureException exception = assertThrows(LectureException.class, () -> {
            lectureService.applyLecture(requestDto);
        });

        assertEquals(ErrorCode.CAPACITY_EXCEEDED, exception.getErrorCode());
    }

    @Test
    public void testApplyLecture_AlreadyApplied() {
        LectureRequestDto requestDto = new LectureRequestDto(4L, 1L);
        LectureDetail lectureDetail = new LectureDetail(4L, 29, 30,1);

        when(lectureDetailRepository.getLectureDetailDomain(requestDto.getLectureDetailId())).thenReturn(lectureDetail);
        when(lectureRegistrationRepository.isApplied(requestDto.getLectureDetailId(), requestDto.getUserId())).thenReturn(true);

        LectureException exception = assertThrows(LectureException.class, () -> {
            lectureService.applyLecture(requestDto);
        });

        assertEquals(ErrorCode.HAS_APPLIED, exception.getErrorCode());
    }

    @Test
    public void testGetLectureList_NoLecturesAvailable() {
        // Mocking lectureDetailRepository to return an empty list
        when(lectureDetailRepository.findAvaliableLectures()).thenReturn(Collections.emptyList());

        // Verify that the exception is thrown
        LectureException exception = assertThrows(LectureException.class, () -> {
            lectureService.getLectureList();
        });

        assertEquals(ErrorCode.NO_LECTURE, exception.getErrorCode());
    }

}