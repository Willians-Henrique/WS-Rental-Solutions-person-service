package com.ws_solution.WS_Rental_Solutions_person_service.controllers;

import com.ws_solution.WS_Rental_Solutions_person_service.entities.Person;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.PersonRepository;
import com.ws_solution.WS_Rental_Solutions_person_service.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {


    private final PersonRepository personRepository;
    private final PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<List<Person>> allPerson() {
        List<Person> person = personService.allPerson();
        return ResponseEntity.ok(person);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Person>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(personService.findAllPaged(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> personById(@PathVariable UUID id) {
        Person person = personService.findPersonById(id);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Person> deletePersonById(@PathVariable UUID id) {
        Person person = personService.deletePersonById(id);
        return ResponseEntity.ok(person);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person newPerson) {
        Person person = personService.createPerson(newPerson);
        return ResponseEntity.ok(person);
    }

}
