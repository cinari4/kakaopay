package com.kakaopay.apisrv.response;

import com.kakaopay.apisrv.utils.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode implements EnumType {
    OK("요청이 성공하였습니다.", HttpStatus.OK),
    BAD_PARAMETER("요청 파라미터가 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND("리소스를 찾지 못했습니다.",  HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("인증에 실패하였습니다.", HttpStatus.UNAUTHORIZED),
    SERVER_ERROR("서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_NOT_FOUND("파일을 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_FILE_FORMAT("올바르지 않은 파일 포맷입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;

    private final HttpStatus httpStatus;

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getText() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
