package com.ws_solution.WS_Rental_Solutions_person_service.services;


import com.ws_solution.WS_Rental_Solutions_person_service.dto.AddressPersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.AddressPerson;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.Person;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.AddressPersonRepository;
import com.ws_solution.WS_Rental_Solutions_person_service.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AddressPersonService {

    private final PersonRepository personRepository;
    private final AddressPersonRepository addressPersonRepository;

    public AddressPerson create(AddressPersonDTO dto) {
        AddressPerson address = new AddressPerson();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setAdditionalData(dto.getAdditionalData());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setStartDate(dto.getStartDate());
        address.setEndDate(dto.getEndDate());
        address.setIsActive(dto.getIsActive());

        // Buscar a pessoa pelo ID e associar ao telefone
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(UUID.fromString(dto.getPersonId()))
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            address.setPerson(person);
        } else {
            throw new RuntimeException("ID de pessoa é obrigatório");
        }

        return addressPersonRepository.save(address);
    }

    public List<AddressPersonDTO> findAllAddressPerson() {
        List<AddressPerson> addresses = addressPersonRepository.findAll();
        return addresses.stream().map(address -> {
            AddressPersonDTO dto = new AddressPersonDTO();
            dto.setId(address.getId().toString()); // ID do telefone
            dto.setStreet(address.getStreet());
            dto.setNumber(address.getNumber());
            dto.setAdditionalData(address.getAdditionalData());
            dto.setNeighborhood(address.getNeighborhood());
            dto.setCity(address.getCity());
            dto.setState(address.getState());
            dto.setCountry(address.getCountry());
            dto.setStartDate(address.getStartDate());
            dto.setEndDate(address.getEndDate());
            dto.setIsActive(address.getIsActive());

            // Verifique se 'person' não é nulo antes de acessar o ID
            if (address.getPerson() != null) {
                dto.setPersonId(address.getPerson().getId().toString()); // ID da pessoa
            } else {
                dto.setPersonId(null); // Ou trate como necessário
            }

            return dto;
        }).collect(Collectors.toList());
    }

    public AddressPersonDTO findById(UUID id) {
        AddressPerson address = addressPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        AddressPersonDTO dto = new AddressPersonDTO();
        dto.setId(address.getId().toString());
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        dto.setAdditionalData(address.getAdditionalData());
        dto.setNeighborhood(address.getNeighborhood());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        dto.setStartDate(address.getStartDate());
        dto.setEndDate(address.getEndDate());
        dto.setIsActive(address.getIsActive());

        if (address.getPerson() != null) {
            dto.setPersonId(address.getPerson().getId().toString());
        } else {
            dto.setPersonId(null);
        }

        return dto;
    }

    public AddressPerson update(UUID id, AddressPersonDTO dto) {
        AddressPerson address = addressPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setAdditionalData(dto.getAdditionalData());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setStartDate(dto.getStartDate());
        address.setEndDate(dto.getEndDate());
        address.setIsActive(dto.getIsActive());

        // Atualizar a pessoa associada, se necessário
        if (dto.getPersonId() != null) {
            Person person = personRepository.findById(UUID.fromString(dto.getPersonId()))
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            address.setPerson(person);
        } else {
            throw new RuntimeException("ID de pessoa é obrigatório");
        }

        return addressPersonRepository.save(address);
    }

    public void delete(UUID id) {
        AddressPerson address = addressPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        addressPersonRepository.delete(address);
    }

}
