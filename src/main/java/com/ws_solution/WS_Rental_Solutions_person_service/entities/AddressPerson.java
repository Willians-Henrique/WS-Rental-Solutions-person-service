package com.ws_solution.WS_Rental_Solutions_person_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "TB_ADDRESSES_PERSON")
public class AddressPerson {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AddressIdPerson", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonId", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Person person;

    @Column(name = "Street", nullable = false, length = 100)
    private String street;

    @Column(name = "Number", nullable = false, length = 20)
    private String number;

    @Column(name = "AdditionalData", length = 255)
    private String additionalData;

    @Column(name = "Neighborhood", nullable = false, length = 100)
    private String neighborhood;

    @Column(name = "City", nullable = false, length = 100)
    private String city;

    @Column(name = "State", nullable = false, length = 2)
    private String state;

    @Column(name = "Country", nullable = false, length = 50)
    private String country;

    @CreationTimestamp
    @Column(name = "StartDate", nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;


}
