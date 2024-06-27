package com.hhplus.lecture.repository;

import com.hhplus.lecture.domain.LectureDetail;
import com.hhplus.lecture.domain.entity.LectureDetailEntity;
import com.hhplus.lecture.error.ErrorCode;
import com.hhplus.lecture.error.LectureException;
import com.hhplus.lecture.repository.orm.LectureDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureDetailRepositoryImpl implements LectureDetailRepository {

    private final LectureDetailJpaRepository lectureDetailJpaRepository;

    @Override
    public LectureDetail getLectureDetailDomain(long lectureDetailId) {
        LectureDetailEntity lectureDetailEntity = lectureDetailJpaRepository.findByLectureDetailId(lectureDetailId)
                .orElseThrow(() -> new LectureException(ErrorCode.NO_LECTURE));
        return lectureDetailEntity.toDomain();
    }

    @Override
    public LectureDetail detailUpdate(LectureDetail lectureDetail) {
        LectureDetailEntity lectureDetailEntity = new LectureDetailEntity(lectureDetail);
        lectureDetailEntity.setCurrentCnt(lectureDetail.getCurrentCnt()+1);
        lectureDetailEntity.setStartDate(LocalDateTime.now());
        lectureDetailJpaRepository.save(lectureDetailEntity);
        return lectureDetailEntity.toDomain();
    }

    @Override
    public List<Long> findAvaliableLectures() {
        List<LectureDetailEntity> lectureDetails = lectureDetailJpaRepository.findAll().stream()
                .filter(detail -> detail.getStartDate().isAfter(LocalDateTime.now()))
                .filter(detail -> detail.getCapacity() > detail.getCurrentCnt())
                .collect(Collectors.toList());

        List<Long> lectureIds = lectureDetails.stream()
                .map(LectureDetailEntity::getLectureId)
                .distinct()
                .collect(Collectors.toList());

        return lectureIds;
    }

}