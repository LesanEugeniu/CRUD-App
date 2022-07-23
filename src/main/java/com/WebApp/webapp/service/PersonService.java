package com.WebApp.webapp.service;

import com.WebApp.webapp.entity.Person;
import com.WebApp.webapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        Optional<Person> personOptional = personRepository
                .findStudentByEmail(person.getEmail());
        if (personOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        boolean exist = personRepository.existsById(personId);
        if (!exist){
            throw new IllegalStateException("person with id " +
                    personId + " does not exist");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId,
                             String firstName,
                             String lastName,
                             String email) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new IllegalStateException("person with id "
                        + personId + " does not exist"));
        if (firstName != null &&
        firstName.length() > 0 &&
                !Objects.equals(person.getFirstName(), firstName)){
            person.setFirstName(firstName);
        }

        if (lastName != null &&
        lastName.length() > 0 &&
        !Objects.equals(person.getLastName(), lastName)){
            person.setLastName(lastName);
        }

        if( email != null &&
                email.length() > 0 &&
        !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional =
                    personRepository.findStudentByEmail(email);
            if (personOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            person.setEmail(email);
        }
    }
}
