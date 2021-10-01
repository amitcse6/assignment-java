package com.am.assignment;


import com.am.assignment.entity.Address;
import com.am.assignment.entity.Person;
import com.am.assignment.entity.User;
import com.am.assignment.repository.PersonRepository;
import com.am.assignment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class PersonRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testCreateUser() {
        Person parent1 = new Person();
        parent1.setFirstName("Arif1");
        parent1.setLastName("Rahman");
        Address address1 = new Address();
        address1.setCity("Dhaka");
        address1.setStreet("Dhaka");
        address1.setZip("1421");
        parent1.setAddress(address1);
        Person savedParent1 = personRepository.save(parent1);
        Person existParent1 = entityManager.find(Person.class, savedParent1.getId());
        assertThat(parent1.getFirstName()).isEqualTo(existParent1.getFirstName());

        Person parent2 = new Person();
        parent2.setFirstName("Arif2");
        parent2.setLastName("Rahman");
        parent2.setParent(parent1);
        Person savedParent2 = personRepository.save(parent2);
        Person existParent2 = entityManager.find(Person.class, savedParent2.getId());
        assertThat(parent2.getFirstName()).isEqualTo(existParent2.getFirstName());
        assertThat(existParent2.getParent().getFirstName()).isEqualTo("Arif1");

        Person parent3 = new Person();
        parent3.setFirstName("Arif3");
        parent3.setLastName("Rahman");
        parent3.setParent(parent2);
        Person savedParent3 = personRepository.save(parent3);
        Person existParent3 = entityManager.find(Person.class, savedParent3.getId());
        assertThat(parent3.getFirstName()).isEqualTo(existParent3.getFirstName());
        assertThat(existParent3.getParent().getFirstName()).isEqualTo("Arif2");
    }
}
