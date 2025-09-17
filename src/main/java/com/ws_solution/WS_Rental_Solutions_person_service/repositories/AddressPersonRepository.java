package com.ws_solution.WS_Rental_Solutions_person_service.repositories;

import com.ws_solution.WS_Rental_Solutions_person_service.entities.AddressPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressPersonRepository extends JpaRepository<AddressPerson, UUID> {
}
