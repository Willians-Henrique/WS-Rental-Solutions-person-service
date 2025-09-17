package com.ws_solution.WS_Rental_Solutions_person_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_PERSONS")
public class Person {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PersonId", nullable = false)
    private UUID id;

    @Column(name = "Name", nullable = false, length = 100)
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;

    @Column(name = "CPF", nullable = false, unique = true, length = 11)
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    private String cpf;

    @Column(name = "RG", nullable = false, length = 20)
    @NotBlank(message = "RG é obrigatório")
    @Size(max = 20, message = "RG deve ter no máximo 20 caracteres")
    private String rg;

    @Column(name = "IssuingAuthority", nullable = false, length = 50)
    @NotBlank(message = "Órgão Emissor é obrigatório")
    @Size(max = 50, message = "Órgão Emissor deve ter no máximo 50 caracteres")
    private String issuingAuthority;

    @Column(name = "RGIssuingState", nullable = false, length = 2)
    @NotBlank(message = "Estado de Emissão do RG é obrigatório")
    @Size(min = 2, max = 2, message = "Estado de Emissão do RG deve ter 2 caracteres")
    private String rgIssuingState;


    @Column(name = "MaritalStatus", nullable = false, length = 50)
    @NotBlank(message = "Estado Civil é obrigatório")
    private String maritalStatus;


    @Column(name = "Profession", length = 100)
    @NotBlank(message = "Profissão é obrigatória")
    @Size(max = 100, message = "Profissão deve ter no máximo 100 caracteres")
    private String profession;

    @Column(name = "Nationality", nullable = false, length = 50)
    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(max = 50, message = "Nacionalidade deve ter no máximo 50 caracteres")
    private String nationality;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "CreatedBy", length = 100)
    private String createdBy = "System";

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<AddressPerson> addressesPerson;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<PhonePerson> phonesPerson;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<EmailPerson> emailsPerson;

}
