package com.example.projetfinal.service;

import com.example.projetfinal.entity.Voiture;
import jakarta.persistence.Column;

import java.util.List;

public interface VoitureService {
    Voiture findVoitureByLicense(String license);
    Voiture findVoitureById(int id) throws Exception;
    //void updateVoiture(int id, int year, int mileage, String model, String licence, double price);
    List<Voiture> findAll();
    void deleteVoitureById(int id);
    Voiture add(Voiture voiture);
    List<Voiture> findVoitureByParam(String year, String mileage, String model, String license, String price);
    List<Voiture> findVoitureByPriceInf(double price);
    List<Voiture> findVoitureByYear(int year);
    List<Voiture> findVoitureByMileage(int mileage);

    List<Voiture> findVoitureNonReserve();
    Voiture findById(int id);

    List<Voiture> findListVoitureDisponible();
}
