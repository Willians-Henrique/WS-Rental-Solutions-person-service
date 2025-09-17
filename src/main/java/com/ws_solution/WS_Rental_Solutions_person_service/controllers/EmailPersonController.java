package com.ws_solution.WS_Rental_Solutions_person_service.controllers;

import com.ws_solution.WS_Rental_Solutions_person_service.dto.EmailPersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.dto.PhonePersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.EmailPerson;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.PhonePerson;
import com.ws_solution.WS_Rental_Solutions_person_service.services.EmailPersonService;
import com.ws_solution.WS_Rental_Solutions_person_service.services.PhonePersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class EmailPersonController {

    private final EmailPersonService emailPersonService;

    @PostMapping("/email")
    public ResponseEntity<?> create(@RequestBody EmailPersonDTO dto) {
        EmailPerson email = emailPersonService.create(dto);
        return ResponseEntity.ok(email);
    }

    @GetMapping("/email/list")
    public List<EmailPersonDTO> findAllEmailPerson() {
        return emailPersonService.findAllEmailsPerson();
    }

    @GetMapping("/email/{id}")
    public EmailPersonDTO findById(@PathVariable UUID id) {

        return emailPersonService.findById(id);
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody EmailPersonDTO dto) {
        EmailPerson updatedEmail = emailPersonService.update(id, dto);
        return ResponseEntity.ok(updatedEmail);
    }
    @DeleteMapping("/email/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        emailPersonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
