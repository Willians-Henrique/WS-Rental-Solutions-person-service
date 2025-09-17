package com.ws_solution.WS_Rental_Solutions_person_service.controllers;

import com.ws_solution.WS_Rental_Solutions_person_service.dto.PhonePersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.PhonePerson;
import com.ws_solution.WS_Rental_Solutions_person_service.services.PhonePersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PhonePersonController {

    private final PhonePersonService phonePersonService;

    @PostMapping("/phone")
    public ResponseEntity<?> create(@RequestBody PhonePersonDTO dto) {
        PhonePerson phone = phonePersonService.create(dto);
        return ResponseEntity.ok(phone);
    }

    @GetMapping("/phone/list")
    public List<PhonePersonDTO> findAllPhonePerson() {
        return phonePersonService.findAllPhonePerson();
    }

    @GetMapping("/phone/{id}")
    public PhonePersonDTO findById(@PathVariable UUID id) {
        return phonePersonService.findById(id);
    }

    @PutMapping("/phone/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody PhonePersonDTO dto) {
        PhonePerson updatedPhone = phonePersonService.update(id, dto);
        return ResponseEntity.ok(updatedPhone);
    }
    @DeleteMapping("/phone/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        phonePersonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
