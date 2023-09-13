package com.example.projetfinal.repository;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture,Integer> {
    @Query(value = "SELECT * FROM voiture v WHERE (v.year != '' AND v.year like %:year%) AND (v.mileage != '' AND v.mileage like %:mileage%) " +
            "AND (v.model != '' AND v.model like %:model%) AND (v.licence != '' AND v.licence like %:licence%) AND (v.price != '' AND v.price like %:price%)", nativeQuery = true)
    List<Voiture> findVoituresByParams(@Param("year") int year, @Param("mileage") int mileage, @Param("model")String model, @Param("licence")String licence, @Param("price")double price) ;

   /** @Query("UPDATE Voiture v SET v.year = :year, v.mileage = :mileage, v.model = :model, v.licence = :licence, v.price = :price WHERE v.id = :id")
    void updateVoiture(
            @Param("id") int id,
            @Param("year") int year,
            @Param("mileage") int mileage,
            @Param("model") String model,
            @Param("licence") String licence,
            @Param("price") double price
    );**/
}
