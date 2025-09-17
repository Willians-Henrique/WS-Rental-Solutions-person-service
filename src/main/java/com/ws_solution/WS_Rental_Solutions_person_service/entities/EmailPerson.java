package com.ws_solution.WS_Rental_Solutions_person_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "TB_EMAIL_PERSON")
public class EmailPerson {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EmailIdPerson", nullable = false)
    private UUID id;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonId", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Person person;

    @Column(name = "EmailAddress", nullable = false, length = 255)
    @NotBlank(message = "Email é obrigatório")
    @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
    @Email(message = "Email deve ser um endereço válido")
    private String email;

    @CreationTimestamp
    @Column(name = "StartDate", nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;



}
