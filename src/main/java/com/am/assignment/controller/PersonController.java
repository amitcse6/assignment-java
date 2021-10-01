package com.am.assignment.controller;

import com.am.assignment.dto.person.PersonRequest;
import com.am.assignment.dto.person.PersonResponse;
import com.am.assignment.dto.privilege.PrivilegeRequest;
import com.am.assignment.service.PersonService;
import com.am.assignment.serviceImpl.PrivilegeServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api(tags = "PersonController")
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok().body(personService.add(personRequest));
    }

    @GetMapping
    public Page<PersonResponse> findAll(Pageable pageable) {
        return personService.findByPage(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PersonRequest personRequest) throws Exception {
        return personService.update(id, personRequest);
    }
}
