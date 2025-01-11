package com.devgang.marketduck.constant;

import lombok.Getter;

public enum ErrorCode {

    BAD_REQUEST(400, "BAD REQUEST", 14001),
    USER_EXIST(400, "USER EXIST", 14002),

    ACCESS_DENIED(403, "ACCESS DENIED", 14003),
    EXPIRED_ACCESS_TOKEN(403, "Expired Access Token", 14004),
    EXPIRED_REFRESH_TOKEN(403, "Expired Refresh Token", 14004),
    NOT_FOUND(404, "NOT FOUND", 14005),
    NOT_FOUND_COOKIE(404, "NOT FOUND COOKIE", 14006),
    ACCESS_DENIED_REQUEST_API(403, "ACCESS_DENIED_REQUEST_API", 14007),
    ARGUMENT_MISMATCH_BAD_REQUEST(400, "ARGUMENT_MISMATCH_BAD_REQUEST", 14008),
    BLOCK_OR_INACTIVE_USER(403, "차단 또는 탈퇴 회원 입니다.", 14009),
    USER_INACTIVE(403, "User Inactive" , 14010),

    NOT_FOUND_USER(404, "회원을 찾을수 없습니다.", 14011),
    WRONG_PASSWORD(403, "비밀번호가 틀렸습니다.", 14013),
    WRONG_VERIFY_NUM(403, "잘못된 인증번호 입니다.", 14014),
    EXPIRED_VERIFY(400, "인증번호가 만료되었습니다.", 14017),
    AWS_BAD_REQUEST(400, "AWS_BAD_REQUEST", 14018),
    AWS_FILE_NOT_FOUND(404, "AWS_FILE_NOT_FOUND.", 14019),
    FILE_NOT_NULL(400, "FILE_NOT_NULL.", 14020),
    COMPLETE_QUESTION(400, "COMPLETE_QUESTION.", 14021),
    AWS_FILE_EXIST(400, "AWS_FILE_EXIST.", 14022),
    NOT_FOUND_MONTHLY_IB(404, "NOT_FOUND_MONTHLY_IB.", 14023),
    BAD_REQUEST_TUTORING(404, "BAD_REQUEST_TUTORING.", 14024),
    IMAGE_LIMIT_EXCEEDED(400, "IMAGE_LIMIT_EXCEEDED.", 14025),
    INVALID_FILE_TYPE(400, "INVALID_FILE_TYPE.", 14026),
    NOT_FOUND_VIDEO_LESSONS_MAIN_CHAPTER(404, "NOT_FOUND_VIDEO_LESSONS_MAIN_CHAPTER.", 14027),
    NOT_FOUND_VIDEO_LESSONS_SUB_CHAPTER(404, "NOT_FOUND_VIDEO_LESSONS_SUB_CHAPTER.", 14028),
    NOT_FOUND_STORAGE_FOLDER(404, "NOT_FOUND_STORAGE_FOLDER.", 14029),
    NOT_FOUND_STORAGE_FILE(404, "NOT_FOUND_STORAGE_FILE.", 14030),
    MAX_FILE_SIZE_EXCEEDED(400, "MAX_FILE_SIZE_EXCEEDED.", 14031),
    NOT_FOUND_ACTIVE_SUBSCRIBE(404, "NOT_FOUND_ACTIVE_SUBSCRIBE.", 14032),
    EXPIRED_OR_NOT_FOUND_VERIFY_NUM(403, "EXPIRED_OR_NOT_FOUND_VERIFY_NUM", 14033),
    TOKEN_NOT_NULL(403, "TOKEN_NOT_NULL", 14034),


    INTERNAL_SERVER_ERROR(500, "Internal Server Error" , 15001),
    DATA_ACCESS_ERROR(500, "Data Access Error", 15002),
    HTTP_REQUEST_IO_ERROR(501, "HTTP_REQUEST_IO_ERROR", 15003),
    NOT_IMPLEMENTED(501, "NOT IMPLEMENTED", 15004),
    AWS_IO_ERROR(500, "AWS_IO_ERROR", 15005),
    FILE_CONVERT_ERROR(500, "FILE_CONVERT_ERROR", 15006);

    @Getter
    private final int status;


    @Getter
    private final int code;

    @Getter
    private final String message;

    ErrorCode(int status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}