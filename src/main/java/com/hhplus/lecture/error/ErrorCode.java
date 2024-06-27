package com.hhplus.lecture.error;

public enum ErrorCode {

    HAS_APPLIED("이미 신청한 강좌입니다."),
    CAPACITY_EXCEEDED("정원이 초과되었습니다."),
    NO_LECTURE("강의가 존재하지 않습니다");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

}
