package com.hhplus.lecture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.lecture.controller.LectureController;
import com.hhplus.lecture.domain.LectureDetail;
import com.hhplus.lecture.dto.LectureRequestDto;
import com.hhplus.lecture.service.LectureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LectureController.class)
public class IntergrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LectureService lectureService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testApplyLecture() throws Exception {
        LectureRequestDto requestDto = new LectureRequestDto(5L, 2L);
        LectureDetail lectureDetail = new LectureDetail(5L, 2, 30);

        when(lectureService.applyLecture(any(LectureRequestDto.class))).thenReturn(lectureDetail);

        mockMvc.perform(MockMvcRequestBuilders.post("/lectures/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currentCnt").value(2))
                .andExpect(jsonPath("$.capacity").value(30));
    }

}