package com.am.assignment.repository;

import com.am.assignment.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    @Override
    Page<Person> findAll(Pageable pageable);
}
