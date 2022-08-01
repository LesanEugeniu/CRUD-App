package com.WebApp.webapp.controller;

import com.WebApp.webapp.entity.Person;
import com.WebApp.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping(path = "api/v1/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getPerson(Model model){
        model.addAttribute("persons", personService.getPersons());
        return "/view/showUsers";
    }

    @GetMapping(path = "/register")
    public String getFormForNewPerson(Model model){
        model.addAttribute("person", new Person());
        return "/view/newPerson";
    }

    @PostMapping(path = "/new")
    public String registerNewPerson(@Valid @ModelAttribute(value = "person") Person person,
                                     BindingResult br){
        if (br.hasErrors()){
            return "/view/newPerson";
        }
            person.setDate(LocalDate.of(2003, 12, 7));
            personService.addNewPerson(person);
            return "redirect:/api/v1/person";
    }

    @GetMapping(path = "/edit/{personId}")
    public String deletePersonForm(Model model,
                                   @PathVariable("personId") Long id){
        Person person = personService.getPersons().stream()
                .filter(p -> p.getId() == id).findAny().orElse(null);
        if (person == null){
            throw new IllegalStateException("Person not find");
        }
        model.addAttribute("person", person);
        return "/view/editPerson";
    }

    @PostMapping(path = "/update/{personId}")
    public String updatePerson(@PathVariable("personId") Long personId,
                               @Valid @ModelAttribute("person") Person person,
                               BindingResult br){
        if(br.hasErrors()){
            return "/view/editPerson";
        }

            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            String email = person.getEmail();
            personService.updatePerson(personId, firstName, lastName, email);
            return "redirect:/api/v1/person";
    }

    @PostMapping(path = "/delete/{personId}")
    public String deletePerson( Model model,
                                @PathVariable("personId") Long personId){
        personService.deletePerson(personId);
        model.addAttribute("person", personService.getPersons());
        return "redirect:/api/v1/person";
    }

    @GetMapping(path = "/id/sender")
    public String idSenderForUpdate(@RequestParam("personId") Long personId){
        return "redirect:/api/v1/person/edit/" + String.valueOf(personId);
    }
}
