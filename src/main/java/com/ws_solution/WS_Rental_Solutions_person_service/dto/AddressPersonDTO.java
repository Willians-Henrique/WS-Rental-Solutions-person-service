package com.ws_solution.WS_Rental_Solutions_person_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressPersonDTO {
    private String id; // ID do endereço
    private String personId; // ID da pessoa associada
    private String street; // Rua
    private String number; // Número
    private String additionalData; // Complemento
    private String neighborhood; // Bairro
    private String city; // Cidade
    private String state; // Estado (2 caracteres)
    private String country; // País
    private LocalDateTime startDate; // Data de início
    private LocalDateTime endDate; // Data de término
    private Boolean isActive;
}