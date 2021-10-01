package com.am.assignment.serviceImpl;

import com.am.assignment.dto.common.CommonResponse;
import com.am.assignment.dto.person.PersonRequest;
import com.am.assignment.dto.person.PersonResponse;
import com.am.assignment.entity.Person;
import com.am.assignment.exception.CommonException;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonResponse add(PersonRequest personRequest) {
        if (Objects.isNull(personRequest.getParent())) {
            Person person = personMapper.getPerson(personRequest);
            person = personRepository.save(person);
            return personMapper.getPersonResponse(person);
        } else {
            Optional<Person> parentOptional = personRepository.findById(personRequest.getParent().getId());
            if (!parentOptional.isPresent()) {
                throw new CommonException(AppMessage.PARENT_NOT_FOUND);
            }
            Person parent = parentOptional.get();
            Person person = personMapper.getPerson(personRequest);
            person.setAddress(null);
            person.setParent(parent);
            person = personRepository.save(person);
            return personMapper.getPersonResponse(person);
        }
    }

    @Override
    public Page<PersonResponse> findByPage(Pageable pageable) {
        Page<Person> personPage = personRepository.findAll(pageable);
        List<PersonResponse> personResponseList = personPage.getContent().stream().map(person -> personMapper.getPersonResponse(person)).collect(Collectors.toList());
        Page<PersonResponse> pages = new PageImpl<>(personResponseList, personPage.getPageable(), personPage.getTotalElements());
        return pages;
    }

    @Override
    public ResponseEntity<?> findBy(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            throw new CommonException(AppMessage.RECORD_NOT_FOUND);
        }
        return new ResponseEntity(personOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(Long id, PersonRequest personRequest) throws Exception {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            throw new CommonException(AppMessage.RECORD_NOT_FOUND);
        }
        Person person = personMapper.setPerson(personOptional.get(), personRequest);
        PersonResponse personResponse = personMapper.getPersonResponse(personRepository.save(person));
        return new ResponseEntity(personResponse, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> delete(Long id) throws Exception {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            throw new CommonException(AppMessage.RECORD_NOT_FOUND);
        }
        Person person = personOptional.get();
        personRepository.deleteById(person.getId());
        return new ResponseEntity(new CommonResponse(AppMessage.RECORD_REMOVE_SUCCESSFULLY), HttpStatus.ACCEPTED);
    }
}
