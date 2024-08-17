package com.devgang.marketduck.advice;

import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.dto.ErrorResponse;
import com.devgang.marketduck.exception.ServiceLogicException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMediaTypeNotSupportedException.class,
            ConstraintViolationException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestExceptions(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            return ErrorResponse.of(((MethodArgumentNotValidException) e).getBindingResult());
        } else if (e instanceof MethodArgumentTypeMismatchException || e instanceof HttpMediaTypeNotSupportedException) {
            return ErrorResponse.of(ErrorCode.ARGUMENT_MISMATCH_BAD_REQUEST);
        } else if (e instanceof ConstraintViolationException) {
            return ErrorResponse.of(((ConstraintViolationException) e).getConstraintViolations());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception e) {
        // 웹훅 로직을 여기에 추가할 수 있습니다.
        e.printStackTrace();
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceLogicException.class)
    public ResponseEntity<ErrorResponse> handleServiceLogicException(ServiceLogicException e) {
        if (e.getErrorCode().getStatus() == 500) {
            // 웹훅 로직을 여기에 추가할 수 있습니다.
            e.printStackTrace();
        }
        return new ResponseEntity<>(
                ErrorResponse.of(e.getErrorCode(), e.getMessage()),
                HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    protected ResponseEntity<ErrorResponse> handleMultipartException(MaxUploadSizeExceededException e) {
        log.error("handleMaxUploadSizeExceededException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.MAX_FILE_SIZE_EXCEEDED);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}