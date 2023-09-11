package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Reservation;
import com.example.projetfinal.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion-reservation")
public class ReservationControleur {

    @Autowired

    private ReservationService reservationService;

    public ReservationControleur (ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String gestionReservations (Model model) {
        List<Reservation> listReservations = reservationService.findAll();
        model.addAttribute("listReservations", listReservations);
        return "gestion-reservations";
    }

    @PostMapping("/delete/{id}")
    public String supprimerReservation (@PathVariable("id") int id) {
        reservationService.deleteReservation(id);
        return "redirect:/gestion-reservation";
    }

}
