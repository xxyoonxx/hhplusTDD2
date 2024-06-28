package com.hhplus.lecture;

import com.hhplus.lecture.domain.LectureDetail;
import com.hhplus.lecture.dto.LectureRequestDto;
import com.hhplus.lecture.repository.LectureDetailRepository;
import com.hhplus.lecture.service.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class LectureServiceTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureDetailRepository lectureDetailRepository;

    @Test
    void multipleApplyTest() throws InterruptedException {
        int numberOfUsers = 35;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfUsers);
        CountDownLatch latch = new CountDownLatch(numberOfUsers);

        for (int i = 0; i < numberOfUsers; i++) {
            long userId = i + 1;
            System.out.println((i+1)+"번째 접근");
            executorService.submit(() -> {
                try {
                    LectureRequestDto dto = new LectureRequestDto(5, userId);
                    assertDoesNotThrow(() -> {
                        lectureService.applyLecture(dto);
                    });
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }

}
