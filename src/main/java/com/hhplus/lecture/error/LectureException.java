package com.hhplus.lecture.error;

import lombok.Getter;

@Getter
public class LectureException extends RuntimeException {

    private final ErrorCode errorCode;

    public LectureException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
