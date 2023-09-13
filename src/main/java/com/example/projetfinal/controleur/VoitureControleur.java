package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.entity.Reservation;
import com.example.projetfinal.service.ReservationService;
import com.example.projetfinal.entity.Voiture;
import com.example.projetfinal.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
public class VoitureControleur {
    @Autowired
    private VoitureService voitureService;

    public VoitureControleur(VoitureService voitureService){
        this.voitureService=voitureService;
    }

    @GetMapping("gestion-voiture")
    public String pageVoiture(Model model, @ModelAttribute("voiture") Voiture voiture) {
        if(voiture.getModel() != null && voiture.getYear() != 0 && voiture.getMileage() != 0 && voiture.getLicense() != null && voiture.getPrice() != 0){
            List<Voiture> list = voitureService.findVoitureByParam(voiture.getYear(),voiture.getMileage(),voiture.getModel(), voiture.getLicense(),voiture.getPrice());
            model.addAttribute("listVoiture",list);
        }else{
            List<Voiture> listVoitures = voitureService.findAll();
            model.addAttribute("voiture",new Voiture());
            model.addAttribute("listVoiture", listVoitures);
        }
        return "gestion-voiture";
    }
    @GetMapping("/voiture/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable("id") int id) throws Exception {
        Voiture voiture=null;
        try{
            voiture = voitureService.findVoitureById(id);
            System.out.println(voiture);
            if(voiture==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            throw new Exception("La voiture nexiste pas");
        }
        return new ResponseEntity<>(voiture, HttpStatus.OK);
    }
    @GetMapping("/voiture")
    public  List<Voiture> getAllVoiture(){
        return  voitureService.findAll();
    }
    @GetMapping("/voiture/price/{price}")
    public List<Voiture> getVoitureByPriceInf(@PathVariable("price") double price){
        List<Voiture> voitures=voitureService.findVoitureByPriceInf(price);
        return voitures;
    }
    @GetMapping("/voiture/year/{year}")
    public List<Voiture> getVoitureByYear(@PathVariable("year") int year){
        List<Voiture> voitures=voitureService.findVoitureByYear(year);
        return voitures;
    }
    @GetMapping("/voiture/mileage/{mileage}")
    public List<Voiture> getVoitureByMileage(@PathVariable("mileage") int mileage){
        List<Voiture> voitures=voitureService.findVoitureByMileage(mileage);
        return voitures;
    }
    @PostMapping("/voiture")
    public Voiture saveVoiture(@RequestBody Voiture voiture){
        return voitureService.add(voiture);
    }

    @GetMapping("/gestion-location-voiture")
    public String gestionLocationVoitures(Model model) {
        List<Voiture> listVoitures = voitureService.findAll();
        model.addAttribute("listVoiture", listVoitures);
        return "gestion-location-voiture";
    }


}
