package com.example.projetfinal.service;

import com.example.projetfinal.entity.Voiture;
import jakarta.persistence.Column;

import java.util.List;

public interface VoitureService {
    Voiture findVoitureByLicense(String license);
    Voiture findVoitureById(int id) throws Exception;
    List<Voiture> findAll();
    void deleteVoitureById(int id);
    Voiture add(Voiture voiture);
    List<Voiture> findVoitureByParam(int year, int mileage, String model, String license, double price);
    List<Voiture> findVoitureByPriceInf(double price);
    List<Voiture> findVoitureByYear(int year);
    List<Voiture> findVoitureByMileage(int mileage);

    List<Voiture> findVoitureNonReserve();
}
