package com.am.assignment.service;

import com.am.assignment.dto.common.CommonFullResponse;
import com.am.assignment.dto.role.RoleRequest;
import com.am.assignment.entity.Role;

import java.util.List;

public interface RoleService {
    CommonFullResponse add(RoleRequest roleRequest);
    Role findByName(String name);
    List<Role> findAll();
}
