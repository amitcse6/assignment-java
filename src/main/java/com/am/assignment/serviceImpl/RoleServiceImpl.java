package com.am.assignment.serviceImpl;

import com.am.assignment.dto.common.CommonFullResponse;
import com.am.assignment.dto.role.RoleRequest;
import com.am.assignment.entity.Privilege;
import com.am.assignment.entity.Role;
import com.am.assignment.exception.CommonException;
import com.am.assignment.repository.PrivilegeRepository;
import com.am.assignment.repository.RoleRepository;
import com.am.assignment.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public CommonFullResponse add(RoleRequest roleRequest) {
        Role role = roleRepository.findByName(roleRequest.getName());
        if (Objects.nonNull(role)) {
            throw new CommonException("Role Already Exists!");
        } else {
            role = new Role();
            role.setName(roleRequest.getName());
            roleRepository.save(role);
            log.info("Role created successfully!!");
            return new CommonFullResponse(HttpStatus.OK.value(), "Role created successfully!!", null);
        }
    }

    public CommonFullResponse append(RoleRequest roleRequest) {
        Role role = roleRepository.findByName(roleRequest.getName());
        if (Objects.nonNull(role)) {
            List<Privilege> privileges = role.getPrivileges();
            if (Objects.nonNull(roleRequest.getPrivileges())) {
                roleRequest.getPrivileges().forEach(s -> {
                    Privilege privilege = new Privilege();
                    privilege.setName(s);
                    privileges.add(privilege);
                });
            }
            role.setPrivileges(privileges);
            roleRepository.save(role);
            log.info("Role Update successfully!!");
            return new CommonFullResponse(HttpStatus.OK.value(), "Role Update successfully!!", null);
        } else {
            throw new CommonException("Role Not Found!");
        }
    }

    public CommonFullResponse update(RoleRequest roleRequest) {
        Role role = roleRepository.findByName(roleRequest.getName());
        if (Objects.nonNull(role)) {
            List<Privilege> privileges = role.getPrivileges();
            if (Objects.nonNull(roleRequest.getPrivileges())) {
                roleRequest.getPrivileges().forEach(s -> {
                    Privilege privilege = new Privilege();
                    privilege.setName(s);
                    privileges.add(privilege);
                });
            }
            role.setPrivileges(privileges);
            roleRepository.save(role);
            log.info("Role Update successfully!!");
            return new CommonFullResponse(HttpStatus.OK.value(), "Role Update successfully!!", null);
        } else {
            throw new CommonException("Role Not Found!");
        }
    }
}
