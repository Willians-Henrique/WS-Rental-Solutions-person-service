package com.ws_solution.WS_Rental_Solutions_person_service.services;


import com.ws_solution.WS_Rental_Solutions_person_service.dto.PhonePersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.Person;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.PhonePerson;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.PersonRepository;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.PhonePersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PhonePersonService {

    public final PhonePersonRepository phonePersonRepository;
    private final PersonRepository personRepository;

    public PhonePerson create(PhonePersonDTO dto) {
        PhonePerson phone = new PhonePerson();
        phone.setTypePhone(dto.getTypePhone());
        phone.setNumberPhone(dto.getNumberPhone());
        phone.setStartDate(dto.getStartDate());
        phone.setEndDate(dto.getEndDate());
        phone.setIsActive(dto.getIsActive());

        // Buscar a pessoa pelo ID e associar ao telefone
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(UUID.fromString(dto.getPersonId()))
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            phone.setPerson(person);
        } else {
            throw new RuntimeException("ID de pessoa é obrigatório");
        }

        return phonePersonRepository.save(phone);
    }

    public List<PhonePersonDTO> findAllPhonePerson() {
        List<PhonePerson> phones = phonePersonRepository.findAll();
        return phones.stream().map(phone -> {
            PhonePersonDTO dto = new PhonePersonDTO();
            dto.setId(phone.getId().toString()); // ID do telefone
            dto.setTypePhone(phone.getTypePhone());
            dto.setNumberPhone(phone.getNumberPhone());
            dto.setStartDate(phone.getStartDate());
            dto.setEndDate(phone.getEndDate());
            dto.setIsActive(phone.getIsActive());

            // Verifique se 'person' não é nulo antes de acessar o ID
            if (phone.getPerson() != null) {
                dto.setPersonId(phone.getPerson().getId().toString()); // ID da pessoa
            } else {
                dto.setPersonId(null); // Ou trate como necessário
            }

            return dto;
        }).collect(Collectors.toList());
    }

    public PhonePersonDTO findById(UUID id) {
        PhonePerson phone = phonePersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        PhonePersonDTO dto = new PhonePersonDTO();
        dto.setId(phone.getId().toString());
        dto.setTypePhone(phone.getTypePhone());
        dto.setNumberPhone(phone.getNumberPhone());
        dto.setStartDate(phone.getStartDate());
        dto.setEndDate(phone.getEndDate());
        dto.setIsActive(phone.getIsActive());

        if (phone.getPerson() != null) {
            dto.setPersonId(phone.getPerson().getId().toString());
        } else {
            dto.setPersonId(null);
        }

        return dto;
    }

    public PhonePerson update(UUID id, PhonePersonDTO dto) {
        PhonePerson phone = phonePersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        phone.setTypePhone(dto.getTypePhone());
        phone.setNumberPhone(dto.getNumberPhone());
        phone.setStartDate(dto.getStartDate());
        phone.setEndDate(dto.getEndDate());
        phone.setIsActive(dto.getIsActive());

        // Atualizar a pessoa associada, se necessário
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(UUID.fromString(dto.getPersonId()))
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            phone.setPerson(person);
        } else {
            throw new RuntimeException("ID de pessoa é obrigatório");
        }

        return phonePersonRepository.save(phone);
    }

    public void delete(UUID id) {
        PhonePerson phone = phonePersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));
        phonePersonRepository.delete(phone);
    }

}
