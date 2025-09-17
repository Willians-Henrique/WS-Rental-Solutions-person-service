package com.ws_solution.WS_Rental_Solutions_person_service.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "TB_PHONES_PERSON")
public class PhonePerson {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PhoneIdPerson", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonId", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Person person;

    @Column(name = "Type", nullable = false, length = 20)
    private String typePhone;

    @NotBlank(message = "Telefone é obrigatório")
    @Column(name = "Number", nullable = false, length = 20)
    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    private String numberPhone;

    @CreationTimestamp
    @Column(name = "StartDate", nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;


}
