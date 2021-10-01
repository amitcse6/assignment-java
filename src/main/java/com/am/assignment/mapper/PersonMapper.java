package com.am.assignment.mapper;

import com.am.assignment.converter.DozerConverter;
import com.am.assignment.dto.person.PersonRequest;
import com.am.assignment.dto.person.PersonResponse;
import com.am.assignment.entity.Address;
import com.am.assignment.entity.Person;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PersonMapper {
    public Person getPerson(PersonRequest personRequest) {
        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        if (Objects.nonNull(personRequest.getAddress())) {
            Address address = new Address();
            address.setCity(personRequest.getAddress().getCity());
            address.setStreet(personRequest.getAddress().getStreet());
            address.setZip(personRequest.getAddress().getZip());
            person.setAddress(address);
        }
        return person;
    }

    public PersonResponse getPersonResponse(Person person) {
        return DozerConverter.parseObject(person, PersonResponse.class);
    }

    public Person setPerson(Person person, PersonRequest personRequest) {
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        if (Objects.nonNull(personRequest.getAddress())) {
            Address address = new Address();
            address.setCity(personRequest.getAddress().getCity());
            address.setStreet(personRequest.getAddress().getStreet());
            address.setZip(personRequest.getAddress().getZip());
            person.setAddress(address);
        }
        return person;
    }
}
