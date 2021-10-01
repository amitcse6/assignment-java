package com.am.assignment.service;

import com.am.assignment.dto.person.PersonRequest;
import com.am.assignment.dto.person.PersonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PersonService {
    PersonResponse add(PersonRequest personRequest);
    Page<PersonResponse> findByPage(Pageable pageable);
    ResponseEntity<?> update(Long id, PersonRequest personRequest) throws Exception;
}
