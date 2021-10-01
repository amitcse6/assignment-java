package com.am.assignment.serviceImpl;

import com.am.assignment.dto.person.PersonRequest;
import com.am.assignment.dto.person.PersonResponse;
import com.am.assignment.entity.Person;
import com.am.assignment.mapper.PersonMapper;
import com.am.assignment.message.AppMessage;
import com.am.assignment.repository.PersonRepository;
import com.am.assignment.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonResponse add(PersonRequest personRequest) {
        return personMapper.getPersonResponse(personRepository.save(personMapper.getPerson(personRequest)));
    }

    @Override
    public Page<PersonResponse> findByPage(Pageable pageable) {
        Page<Person> personPage = personRepository.findAll(pageable);
        List<PersonResponse> personResponseList = personPage.getContent().stream().map(person -> personMapper.getPersonResponse(person)).collect(Collectors.toList());
        Page<PersonResponse> pages = new PageImpl<>(personResponseList, personPage.getPageable(), personPage.getTotalElements());
        return pages;
    }

    @Override
    public ResponseEntity<?> update(Long id, PersonRequest personRequest) throws Exception {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            throw new Exception(AppMessage.PERSON_NOT_FOUND);
        }
        Person person = personMapper.setPerson(personOptional.get(), personRequest);
        PersonResponse personResponse = personMapper.getPersonResponse(personRepository.save(person));
        return new ResponseEntity(personResponse, HttpStatus.ACCEPTED);
    }
}
