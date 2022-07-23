package com.WebApp.webapp.config;

import com.WebApp.webapp.entity.Person;
import com.WebApp.webapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository){
        return args ->{
            Person person = new Person("Lesan", "Eugeniu",
                    "eugeniu1337@gmail.com", LocalDate.of(2003,12,7));
            Person person1 = new Person("Lesan", "Mirela",
                    "ellalesan@gmail.com", LocalDate.of(2001,2,24));
            personRepository.save(person);
            personRepository.save(person1);

        };
    }
}
