package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.entity.Voiture;
import com.example.projetfinal.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoitureControleur {
    @Autowired
    private VoitureService voitureService;
    public VoitureControleur(VoitureService voitureService){
        this.voitureService=voitureService;
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
    @PostMapping("/voiture")
    public Voiture saveVoiture(@RequestBody Voiture voiture){
        return voitureService.add(voiture);
    }
}
