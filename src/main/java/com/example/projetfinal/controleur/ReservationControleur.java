package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Reservation;
import com.example.projetfinal.service.ReservationService;
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

    @GetMapping("/{id}")
    public String gestionReservations(@PathVariable("id") int id, Model model) {

        Client client = clientService.findClientById(id);

        List<Reservation> listReservations = client.getReservations();

        model.addAttribute("listReservations", listReservations);

        return "gestion-reservations";
    }

    @GetMapping("/reservation-form")
    public String gestionReservationForm(Model model){
        model.addAttribute("reservation",new Reservation());
        model.addAttribute("client",new Client());
        model.addAttribute("voiture",new Voiture(1, 2020, 38063, "Suburban 1500", "HFR-943", 1643.0));
        model.addAttribute("listClient",clientService.findAllClients());
        model.addAttribute("listVoiture",voitureService.findVoitureNonReserve());
        return "reservation-form";
    }

    @GetMapping("/reservation-form/{id}")
    public String getReservationFormUpdate(@PathVariable("id") int id,Model model){
        model.addAttribute("reservation",reservationService.findById(id));
        model.addAttribute("client",reservationService.findById(id).getClient());
        model.addAttribute("voiture",reservationService.findById(id).getVoiture());
        model.addAttribute("listClient",clientService.findAllClients());
        model.addAttribute("listVoiture",voitureService.findVoitureNonReserve());
        return "reservation-form";
    }

    @PostMapping ("/reservation-form/save")
    public String saveReservation(Model model,@ModelAttribute("reservation") Reservation reservation){
        System.out.println(reservation.getDate());
        if(!reservationRepository.existsById(reservation.getId())){
            reservationRepository.save(reservation);
        }else{
            Reservation reservationExistant = reservationRepository.getReferenceById(reservation.getId());
            reservationExistant.setClient(reservation.getClient());
            reservationExistant.setEmploye(reservation.getEmploye());
            reservationExistant.setDate(reservation.getDate());
            reservationExistant.setVoiture(reservation.getVoiture());
            reservationRepository.save(reservationExistant);
        }
        return "redirect:/gestion-reservations";
    }


    @PostMapping ("/save")
    public String creerReservation(Model model,@ModelAttribute("reservation") Reservation reservation) {
        System.out.println(reservation.getDate());
        if (!reservationRepository.existsById(reservation.getId())) {
            reservationRepository.save(reservation);
        } else {
            Reservation reservationExistant = reservationRepository.getReferenceById(reservation.getId());
            reservationExistant.setClient(reservation.getClient());
            reservationExistant.setEmploye(reservation.getEmploye());
            reservationExistant.setDate(reservation.getDate());
            reservationExistant.setVoiture(reservation.getVoiture());
            reservationRepository.save(reservationExistant);
        }
        return "redirect:/gestion-reservations";
    }
        @GetMapping("/reservation/details/{id}")
        public String reservationDetails ( @PathVariable int id, Model model){
            Reservation reservation = reservationService.findById(id);
            model.addAttribute("reservation", reservation);
            return "details-reservation";
        }

        @GetMapping("/reservation/voiture/{id}")
        public String reserverVoiture ( @PathVariable int id, Model model){

            Voiture voiture = voitureService.findById(id);
            if (voiture != null && voiture.isDisponible()) {
                return "redirect:/gestion-reservations";
            } else {
                return "redirect:/details-reservation";
            }
        }

}
