package com.example.projetfinal.entity;

import com.example.projetfinal.repository.VoitureRepository;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Date date;
    String employe;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="reservation_voiture_FK")
    private Voiture voiture;

}
