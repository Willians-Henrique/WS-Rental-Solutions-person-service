package com.ws_solution.WS_Rental_Solutions_person_service.controllers;

import com.ws_solution.WS_Rental_Solutions_person_service.dto.AddressPersonDTO;
import com.ws_solution.WS_Rental_Solutions_person_service.entities.AddressPerson;
import com.ws_solution.WS_Rental_Solutions_person_service.services.AddressPersonService;
import com.ws_solution.WS_Rental_Solutions_person_service.services.EmailPersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class AddressPersonController {

    private final EmailPersonService emailPersonService;
    private final AddressPersonService addressPersonService;

    @PostMapping("/address")
    public ResponseEntity<?> create(@RequestBody AddressPersonDTO dto) {
        AddressPerson address = addressPersonService.create(dto);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/list")
    public List<AddressPersonDTO> findAllAddressPerson() {

        return addressPersonService.findAllAddressPerson();
    }

    @GetMapping("/address/{id}")
    public AddressPersonDTO findById(@PathVariable UUID id) {

        return addressPersonService.findById(id);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody AddressPersonDTO dto) {
        AddressPerson updatedAddress = addressPersonService.update(id, dto);
        return ResponseEntity.ok(updatedAddress);
    }
    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        addressPersonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
