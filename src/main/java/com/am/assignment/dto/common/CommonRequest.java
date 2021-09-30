package com.am.assignment.dto.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
}
