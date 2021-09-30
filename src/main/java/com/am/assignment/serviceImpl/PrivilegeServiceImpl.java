package com.am.assignment.serviceImpl;


import com.am.assignment.dto.common.CommonFullResponse;
import com.am.assignment.dto.privilege.PrivilegeRequest;
import com.am.assignment.entity.Privilege;
import com.am.assignment.exception.CommonException;
import com.am.assignment.repository.PrivilegeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PrivilegeServiceImpl {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public List<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    public Privilege findByName(String name) {
        return privilegeRepository.findByName(name);
    }

    public CommonFullResponse add(PrivilegeRequest privilegeRequest) {
        Privilege privilege = privilegeRepository.findByName(privilegeRequest.getName());
        if (Objects.nonNull(privilege)) {
            throw new CommonException("Privilege Already Exists!");
        } else {
            privilege = new Privilege();
            privilege.setName(privilegeRequest.getName());
            privilegeRepository.save(privilege);
            log.info("Privilege created successfully!!");
            return new CommonFullResponse(HttpStatus.OK.value(), "Privilege created successfully!!", null);
        }
    }
}
