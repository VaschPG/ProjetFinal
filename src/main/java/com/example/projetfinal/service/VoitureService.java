package com.example.projetfinal.service;

import com.example.projetfinal.entity.Voiture;

import java.util.List;

public interface VoitureService {
    Voiture findVoitureByLicense(String license);
    Voiture findVoitureById(int id) throws Exception;
    List<Voiture> findAll();
    Voiture add(Voiture voiture);
    List<Voiture> findVoitureByPriceInf(double price);
    List<Voiture> findVoitureByYear(int year);
    List<Voiture> findVoitureByMileage(int mileage);
}
