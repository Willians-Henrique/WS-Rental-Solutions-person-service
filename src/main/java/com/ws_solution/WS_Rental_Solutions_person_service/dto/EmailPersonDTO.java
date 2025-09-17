package com.ws_solution.WS_Rental_Solutions_person_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailPersonDTO {
    private String id; // ID do email
    private String emailAddress;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;
    private String personId; // ID da pessoa
}