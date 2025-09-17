package com.ws_solution.WS_Rental_Solutions_person_service.services;


import com.ws_solution.WS_Rental_Solutions_person_service.dto.EmailPersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.dto.PhonePersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.EmailPerson;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.Person;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.PhonePerson;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.EmailPersonRepository;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.PersonRepository;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.PhonePersonRepository;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmailPersonService {

    private final PersonRepository personRepository;
    private final EmailPersonRepository emailPersonRepository;

    public EmailPerson create(EmailPersonDTO dto) {
        EmailPerson email = new EmailPerson();
        email.setEmail(dto.getEmailAddress());
        email.setStartDate(dto.getStartDate());
        email.setEndDate(dto.getEndDate());
        email.setIsActive(dto.getIsActive());

        // Buscar a pessoa pelo ID e associar ao telefone
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(UUID.fromString(dto.getPersonId()))
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            email.setPerson(person);
        } else {
            throw new RuntimeException("ID de pessoa é obrigatório");
        }

        return emailPersonRepository.save(email);
    }

    public List<EmailPersonDTO> findAllEmailsPerson() {
        List<EmailPerson> emails = emailPersonRepository.findAll();
        return emails.stream().map(email -> {
            EmailPersonDTO dto = new EmailPersonDTO();
            dto.setId(email.getId().toString()); // ID do telefone
            dto.setEmailAddress(email.getEmail());
            dto.setStartDate(email.getStartDate());
            dto.setEndDate(email.getEndDate());
            dto.setIsActive(email.getIsActive());

            // Verifique se 'person' não é nulo antes de acessar o ID
            if (email.getPerson() != null) {
                dto.setPersonId(email.getPerson().getId().toString()); // ID da pessoa
            } else {
                dto.setPersonId(null); // Ou trate como necessário
            }

            return dto;
        }).collect(Collectors.toList());
    }

    public EmailPersonDTO findById(UUID id) {
        EmailPerson email = emailPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));

        EmailPersonDTO dto = new EmailPersonDTO();
        dto.setId(email.getId().toString());
        dto.setEmailAddress(email.getEmail());
        dto.setStartDate(email.getStartDate());
        dto.setEndDate(email.getEndDate());
        dto.setIsActive(email.getIsActive());

        if (email.getPerson() != null) {
            dto.setPersonId(email.getPerson().getId().toString());
        } else {
            dto.setPersonId(null);
        }

        return dto;
    }

    public EmailPerson update(UUID id, EmailPersonDTO dto) {
        EmailPerson email = emailPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));

        email.setEmail(dto.getEmailAddress());
        email.setStartDate(dto.getStartDate());
        email.setEndDate(dto.getEndDate());
        email.setIsActive(dto.getIsActive());

        // Atualizar a pessoa associada, se necessário
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(UUID.fromString(dto.getPersonId()))
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            email.setPerson(person);
        } else {
            throw new RuntimeException("ID de pessoa é obrigatório");
        }

        return emailPersonRepository.save(email);
    }

    public void delete(UUID id) {
        EmailPerson email = emailPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));
        emailPersonRepository.delete(email);
    }

}
