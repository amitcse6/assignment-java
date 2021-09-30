package com.am.assignment.controller;

import com.am.assignment.dto.role.RoleRequest;
import com.am.assignment.serviceImpl.RoleServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/role")
@Api(tags = "RoleController")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok().body(roleServiceImpl.add(roleRequest));
    }

    @PostMapping("/append")
    public ResponseEntity<?> append(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok().body(roleServiceImpl.append(roleRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok().body(roleServiceImpl.update(roleRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(roleServiceImpl.findAll());
    }
}
