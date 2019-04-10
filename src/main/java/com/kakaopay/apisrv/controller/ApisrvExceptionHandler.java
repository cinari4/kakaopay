package com.kakaopay.apisrv.controller;

import com.kakaopay.apisrv.exception.ApiException;
import com.kakaopay.apisrv.response.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order
@RestControllerAdvice
public class ApisrvExceptionHandler {
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        return new ResponseEntity<>(ApiResponseDto.createException(ex), ex.getStatus().getHttpStatus());
    }
}
