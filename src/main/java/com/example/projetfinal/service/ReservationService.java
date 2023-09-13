package com.example.projetfinal.service;

import com.example.projetfinal.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    Reservation deleteReservation(int id);
    Reservation findById(int id);

}
