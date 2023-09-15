package com.example.projetfinal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date;
    String employe;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="reservation_voiture_FK")
    private Voiture voiture;


}
