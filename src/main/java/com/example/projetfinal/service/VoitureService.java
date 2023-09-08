package com.example.projetfinal.service;

import com.example.projetfinal.entity.Voiture;

import java.util.List;

public interface VoitureService {
    Voiture findVoitureByLicense(String license);
    Voiture findVoitureById(int id);

    List<Voiture> findAll();
}
