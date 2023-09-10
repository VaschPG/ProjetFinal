package com.example.projetfinal.repository;

import com.example.projetfinal.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Query(value = "SELECT * FROM client c WHERE (c.nom != '' AND c.nom like %:nom%) AND (c.telephone != '' AND c.telephone like %:telephone%) " +
            "AND (c.adresse != '' AND c.adresse like %:adresse%)", nativeQuery = true)
    List<Client> findClientsByParams(@Param("nom") String nom, @Param("telephone") String telephone, @Param("adresse")String adresse);
}
