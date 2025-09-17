package com.ws_solution.WS_Rental_Solutions_person_service.services;

import com.ws_solution.WS_Rental_Solutions_person_service.entities.Person;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PersonService {

    public final PersonRepository personRepository;

    public List<Person> allPerson() {
        return personRepository.findAll();
    }

    public Page<Person> findAllPaged(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Person findPersonById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pessoa não Encontrada"));
    }

    @Transactional
    public Person deletePersonById(UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não Encontrada"));

        // Força o carregamento das listas para deletar
        person.getAddressesPerson().clear();
        person.getPhonesPerson().clear();
        person.getEmailsPerson().clear();

        personRepository.delete(person);
        return person;
    }

    public Person updatePerson(UUID id, Person updatedPerson) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não Encontrada"));

        person.setName(updatedPerson.getName());
        person.setCpf(updatedPerson.getCpf());
        person.setRg(updatedPerson.getRg());
        person.setIssuingAuthority(updatedPerson.getIssuingAuthority());
        person.setRgIssuingState(updatedPerson.getRgIssuingState());
        person.setMaritalStatus(updatedPerson.getMaritalStatus());
        person.setProfession(updatedPerson.getProfession());
        person.setNationality(updatedPerson.getNationality());

        return personRepository.save(person);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
}



