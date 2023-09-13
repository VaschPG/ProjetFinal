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
    public VoitureServiceImpl(){}
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

 /**   @Override
   public void updateVoiture(int id, int year, int mileage, String model, String licence, double price) {
        voitureRepository.updateVoiture(id, year, mileage, model, licence, price);
    }**/

    @Override
    public List<Voiture> findAll() {
        return voitureRepository.findAll();
    }

    @Override
    public void deleteVoitureById(int id) {
        if(voitureRepository.existsById(id))
            voitureRepository.deleteById(id);
    }

    @Override
    public Voiture add(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @Override
    public List<Voiture> findVoitureByParam(int year, int mileage, String model, String license, double price) {
        return voitureRepository.findVoituresByParams(year,mileage,model,license,price);
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
        List<Voiture> voitures= voitureRepository.findAll();
        List<Voiture> voitureYear= new ArrayList<>();
        for(Voiture voiture: voitures){
            if (voiture.getYear()==year){
                voitureYear.add(voiture);
            }
        }
        return voitureYear;
    }

    @Override
    public List<Voiture> findVoitureByMileage(int mileage) {
        List<Voiture> voitures=voitureRepository.findAll();
        List<Voiture> voitureMileage=new ArrayList<>();
        for (Voiture voiture:voitures){
            if (voiture.getMileage()==mileage){
                voitureMileage.add(voiture);
            }
        }
        return voitureMileage;
    }
}
