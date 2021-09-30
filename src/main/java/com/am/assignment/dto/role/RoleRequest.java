package com.am.assignment.dto.role;

import lombok.Data;

import java.util.List;

@Data
public class RoleRequest {
    private Long id;
    private String name;
    private List<String> privileges;
}
