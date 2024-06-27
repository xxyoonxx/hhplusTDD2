package com.hhplus.lecture.error;

public enum ErrorCode {

    HAS_APPLIED("이미 신청한 강좌입니다."),
    CAPACITY_EXCEEDED("정원이 초과되었습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

}
