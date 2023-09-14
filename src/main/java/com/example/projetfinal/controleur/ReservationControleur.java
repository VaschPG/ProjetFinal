package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Reservation;
import com.example.projetfinal.entity.Voiture;
import com.example.projetfinal.service.ReservationService;
import com.example.projetfinal.service.ReservationServiceImpl;
import com.example.projetfinal.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion-reservations")
public class ReservationControleur {

    @Autowired

    private ReservationService reservationService;

    @Autowired
    private VoitureService voitureService;

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
        return "redirect:/gestion-reservations";
    }

    @GetMapping("/reservation/details/{id}")
    public String reservationDetails(@PathVariable int id, Model model) {
        Reservation reservation = reservationService.findById(id);
        model.addAttribute("reservation",reservation);
        return "details-reservation";
    }

    @GetMapping ("/reservation/voiture/{id}")
    public String reserverVoiture(@PathVariable int id, Model model) {

        Voiture voiture = voitureService.findById(id);
        if (voiture != null && voiture.isDisponible()) {
            return "redirect:/gestion-reservations";
        } else {
            return "redirect:/details-reservation";
        }
    }
}
