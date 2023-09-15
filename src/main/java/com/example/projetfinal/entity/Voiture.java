package com.example.projetfinal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="voiture")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer year;
    Integer mileage;
    String model;
    @Column(unique = true)
    String license;
    Double price;
    boolean disponible;

    //Integer reservationId;


}
