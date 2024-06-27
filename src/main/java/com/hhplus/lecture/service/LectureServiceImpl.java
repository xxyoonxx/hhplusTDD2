package com.hhplus.lecture.service;

import com.hhplus.lecture.domain.Lecture;
import com.hhplus.lecture.domain.LectureDetail;
import com.hhplus.lecture.domain.LectureRegistration;
import com.hhplus.lecture.dto.LectureRequestDto;
import com.hhplus.lecture.error.ErrorCode;
import com.hhplus.lecture.error.LectureException;
import com.hhplus.lecture.repository.LectureDetailRepository;
import com.hhplus.lecture.repository.LectureRegistrationRepository;
import com.hhplus.lecture.repository.LectureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final LectureDetailRepository lectureDetailRepository;
    private final LectureRegistrationRepository lectureRegistrationRepository;

    @Override
    @Transactional
    public LectureDetail applyLecture(LectureRequestDto dto) {
        long lectureDetailId = dto.getLectureDetailId();
        long userId = dto.getUserId();

        LectureRegistration lectureRegistration = new LectureRegistration(lectureDetailId, userId);
        LectureDetail lectureDetail = lectureDetailRepository.getLectureDetailDomain(lectureDetailId);

        // 이미 신청했는지
        if(lectureRegistrationRepository.isApplied(lectureDetailId, userId)) throw new LectureException(ErrorCode.HAS_APPLIED);
        // 정원이 초과됐는지
        if(lectureDetail.getCurrentCnt() >= lectureDetail.getCapacity()) throw new LectureException(ErrorCode.CAPACITY_EXCEEDED);

        // 신청 처리
        lectureRegistrationRepository.applyLecture(lectureRegistration);
        return lectureDetailRepository.detailUpdate(lectureDetail);
    }

    /**
     * 특강 목록 조회
     * @return
     */
    @Override
    public List<Lecture> getLectureList() {
        List<Long> lectureIds = lectureDetailRepository.findAvaliableLectures();
        if(lectureIds.isEmpty()) throw new LectureException(ErrorCode.NO_LECTURE);
        List<Lecture> lectureList = lectureRepository.findAllById(lectureIds);
        return lectureList;
    }

    /**
     * 특강 신청 완료 리스트 반환
     * @param userId
     * @return
     */
    @Override
    public List<Lecture> getAppliedLectureList(long userId) {
        List<Lecture> appliedLectures = lectureRepository.findAppliedLectures(userId);
        return appliedLectures;
    }

}
