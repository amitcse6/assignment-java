package com.am.assignment.controller;

import com.am.assignment.dto.privilege.PrivilegeRequest;
import com.am.assignment.serviceImpl.PrivilegeServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "PrivilegeController")
@RequestMapping(value = "/privilege")
public class PrivilegeController {

    private final PrivilegeServiceImpl privilegeServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PrivilegeRequest privilegeRequest) {
        return ResponseEntity.ok().body(privilegeServiceImpl.add(privilegeRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<?> add() {
        return ResponseEntity.ok().body(privilegeServiceImpl.findAll());
    }
}
