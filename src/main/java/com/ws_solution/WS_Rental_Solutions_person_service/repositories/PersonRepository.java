package com.ws_solution.WS_Rental_Solutions_person_service.repositories;

import com.ws_solution.WS_Rental_Solutions_person_service.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

}
