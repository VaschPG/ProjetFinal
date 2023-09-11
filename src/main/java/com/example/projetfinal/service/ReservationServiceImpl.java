package com.example.projetfinal.service;

import com.example.projetfinal.entity.Reservation;
import com.example.projetfinal.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation deleteReservation(int id) {
        return null;
    }

    @Override
    public Reservation findById(int id) {
        return null;
    }
}
