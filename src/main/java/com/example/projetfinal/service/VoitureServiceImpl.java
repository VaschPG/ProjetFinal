package com.example.projetfinal.service;

import com.example.projetfinal.entity.Voiture;
import com.example.projetfinal.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoitureServiceImpl implements VoitureService {
    @Autowired
    private VoitureRepository voitureRepository;
    public VoitureServiceImpl(VoitureRepository voitureRepository){
        this.voitureRepository=voitureRepository;
    }
    @Override
    public Voiture findVoitureByLicense(String license) {
        return null;
    }

    @Override
    public Voiture findVoitureById(int id) throws Exception {
        Voiture voiture=null;
        Optional<Voiture> optionalVoiture= voitureRepository.findById(id);
        if(optionalVoiture.isPresent()){
            voiture=optionalVoiture.get();
        }else{
            throw  new Exception();
        }
        return voiture;
    }

    @Override
    public List<Voiture> findAll() {
        return voitureRepository.findAll();
    }

    @Override
    public Voiture add(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @Override
    public List<Voiture> findVoitureByPriceInf(double price) {
        List<Voiture> voitures=voitureRepository.findAll();
        List<Voiture> voiturePrixInf=new ArrayList<>();
        for(Voiture voiture: voitures){
            if(voiture.getPrice()<price){
                voiturePrixInf.add(voiture);
            }
        }
        return voiturePrixInf;
    }

    @Override
    public List<Voiture> findVoitureByYear(int year) {
        return null;
    }

    @Override
    public List<Voiture> findVoitureByMileage(int mileage) {
        return null;
    }
}
