package com.am.assignment.service;

import com.am.assignment.dto.common.CommonFullResponse;
import com.am.assignment.dto.privilege.PrivilegeRequest;
import com.am.assignment.entity.Privilege;

import java.util.List;

public interface PrivilegeService {
    CommonFullResponse add(PrivilegeRequest privilegeRequest);
    List<Privilege> findAll();
    Privilege findByName(String name);
}
