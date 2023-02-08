package com.bootcamp.greencommute.exception;

import com.bootcamp.greencommute.dto.ExceptionResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler{


    @ExceptionHandler
    public final ResponseEntity<ExceptionResponseDTO> handleException(Exception exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;

        if (exception instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST;
        }
        else if (exception instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }
        else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        String message = exception.getMessage();
        return handleExceptionInternal(exception, new ExceptionResponseDTO(status.getReasonPhrase(), message), headers, status, request);
    }

    protected ResponseEntity<ExceptionResponseDTO> handleExceptionInternal(Exception exception, ExceptionResponseDTO body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception,  RequestAttributes.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
