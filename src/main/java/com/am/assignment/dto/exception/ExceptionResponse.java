package com.am.assignment.dto.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private LocalDateTime time;
    private String message;
    @JsonIgnore
    private String details;
    private String uri;
    private HashMap<String, String> errors;
}
