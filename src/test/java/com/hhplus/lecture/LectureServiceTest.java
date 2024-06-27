package com.hhplus.lecture;

import com.hhplus.lecture.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LectureServiceTest {

    @Autowired
    private LectureService lectureService;

}
