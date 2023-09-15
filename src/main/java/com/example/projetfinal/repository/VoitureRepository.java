package com.example.projetfinal.repository;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture,Integer> {
    @Query(value = "SELECT * FROM voiture v WHERE (v.year is not NULL AND v.year like :year%) AND (v.mileage is not NULL AND v.mileage like :mileage%) " +
            "AND (v.model != '' AND v.model like %:model%) AND (v.license != '' AND v.license like %:license%) AND (v.price is not NULL AND v.price like :price%)", nativeQuery = true)
    List<Voiture> findVoituresByParams(@Param("year") String year, @Param("mileage") String mileage, @Param("model")String model, @Param("license")String license, @Param("price")String price);
}
