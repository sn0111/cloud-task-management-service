package com.pesto.tech.util;

import com.pesto.tech.data.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error("DataIntegrityViolationException error message: {}", ex.getMessage());
        ResponseDTO responseDto = ResponseBuilder.buildErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getRootCause() != null ? ex.getRootCause().getLocalizedMessage() : ex.getLocalizedMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException error message: {}", ex.getMessage());
        ResponseDTO responseDto = ResponseBuilder.buildErrorResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDTO> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("EmrServiceException error message: {}", ex.getMessage());
        ResponseDTO responseDto = ResponseBuilder.buildErrorResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException error message: {}", ex.getMessage());
        ResponseDTO responseDto = ResponseBuilder.buildErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException error message: {}", ex.getMessage());
        List<Map<String, String>> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            Map<String, String> errorMap = new HashMap<>();
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put("msg", String.format("%s %s", fieldName, message));
            errors.add(errorMap);
        });
        ResponseDTO responseDto = ResponseBuilder.buildErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), errors);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGlobalException(Exception ex) {
        log.error("Exception error message: {}", ex.getMessage());
        ResponseDTO responseDto = ResponseBuilder.buildErrorResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getLocalizedMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
