package com.example.projetfinal.service;

import com.example.projetfinal.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    void deleteReservation(int id);
    Reservation findById(int id);

    List<Reservation> findAllClientReservationsById(int id);
}
