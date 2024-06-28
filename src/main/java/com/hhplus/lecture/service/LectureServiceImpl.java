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
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

        System.out.println("접근유저: " + userId);
        try {
            // 강의 상세 정보를 로드
            LectureRegistration lectureRegistration = new LectureRegistration(lectureDetailId, userId);
            LectureDetail lectureDetail = lectureDetailRepository.getLectureDetailDomain(lectureDetailId); //

            // 정원이 초과됐는지 확인
            if (lectureDetail.getCurrentCnt() >= 30) {
                throw new LectureException(ErrorCode.CAPACITY_EXCEEDED);
            }

            // 이미 신청했는지 확인
            if (lectureRegistrationRepository.isApplied(lectureDetailId, userId)) {
                throw new LectureException(ErrorCode.HAS_APPLIED);
            }

            // 신청 처리
            lectureRegistrationRepository.applyLecture(lectureRegistration);

            // 강의 상세 정보 업데이트
            return lectureDetailRepository.detailUpdate(lectureDetail);
        } catch (OptimisticLockingFailureException e) {
            throw new LectureException(ErrorCode.CAPACITY_EXCEEDED);
        }
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
