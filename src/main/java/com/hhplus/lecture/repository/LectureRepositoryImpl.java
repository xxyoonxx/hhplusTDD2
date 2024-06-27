package com.hhplus.lecture.repository;

import com.hhplus.lecture.domain.Lecture;
import com.hhplus.lecture.domain.entity.LectureEntity;
import com.hhplus.lecture.domain.entity.LectureRegistrationEntity;
import com.hhplus.lecture.repository.orm.LectureJpaRepository;
import com.hhplus.lecture.repository.orm.LectureRegistrationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;
    private final LectureRegistrationJpaRepository lectureRegistrationJpaRepository;

    @Override
    public List<Lecture> findAppliedLectures(long userId) {
        List<LectureRegistrationEntity> userRegistrations = lectureRegistrationJpaRepository.findByUserId(userId);
        List<Long> userRegisteredLectureIds = userRegistrations.stream()
                .map(registration -> registration.getLectureDetailId())
                .collect(Collectors.toList());
        List<LectureEntity> appliedLectures = lectureJpaRepository.findByLectureIdIn(userRegisteredLectureIds);
        List<Lecture> lectureDomains = entityToDomain(appliedLectures);

        return lectureDomains;
    }

    @Override
    public List<Lecture> findAllById(List<Long> lectureIds) {
        List<LectureEntity> lectureList = lectureJpaRepository.findAllById(lectureIds);
        List<Lecture> lectureDomains = entityToDomain(lectureList);
        return lectureDomains;
    }

    public List<Lecture> entityToDomain(List<LectureEntity> lectureEntitylist){
        List<Lecture> lectureList = lectureEntitylist.stream()
                .map(LectureEntity::toDomain)
                .collect(Collectors.toList());
        return lectureList;
    }

}