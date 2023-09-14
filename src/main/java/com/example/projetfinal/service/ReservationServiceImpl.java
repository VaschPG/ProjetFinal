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
    public void deleteReservation(int id) {
        if(reservationRepository.existsById(id)){
            reservationRepository.deleteById(id);
        }
    }

    @Override
    public Reservation findById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

}
