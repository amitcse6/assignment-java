package com.am.assignment;


import com.am.assignment.dto.exception.ExceptionResponse;
import com.am.assignment.exception.CommonException;
import com.am.assignment.exception.UserExistsException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${security.app.errorDebugEnable}")
    private boolean errorDebugEnable;

    @ExceptionHandler({UserExistsException.class})
    public final ResponseEntity<Object> handleAlreadyExistsException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(getExceptionResponse(ex, request, HttpStatus.CONFLICT, null), HttpStatus.CONFLICT);
    }

    private ExceptionResponse getExceptionResponse(Exception ex, WebRequest request, HttpStatus httpStatus, HashMap<String, String> errors) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTime(LocalDateTime.now());
        exceptionResponse.setUri(((ServletWebRequest) request).getRequest().getRequestURI());
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setErrors(errors);
        if (errorDebugEnable) {
            exceptionResponse.setMessage(ex.getLocalizedMessage());
            exceptionResponse.setDetails(ex.getMessage());
        }
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        String userEmail = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && !authentication.getPrincipal().equals("anonymousUser")) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            if (Objects.nonNull(user)) {
                userEmail = String.valueOf(user.getUsername());
            }
        }
        MDC.put("uri", uri);
        MDC.put("userEmail", userEmail);
        log.error(ex.getMessage(), ex);
        return exceptionResponse;
    }

    @ExceptionHandler({CommonException.class})
    public final ResponseEntity<Object> handleCommonException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(getExceptionResponse(ex, request, HttpStatus.BAD_REQUEST, null), HttpStatus.BAD_REQUEST);
    }
}
