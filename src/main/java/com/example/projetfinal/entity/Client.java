package com.example.projetfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String prenom;
    String nom;
    String telephone;
    String adresse;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public void add(Reservation reservation){
        if(reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
        reservation.setClient(this);
    }
}
