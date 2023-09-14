package com.example.projetfinal;

import com.example.projetfinal.entity.Voiture;
import com.example.projetfinal.repository.VoitureRepository;
import com.example.projetfinal.service.VoitureServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class VoitureServiceImplTest {
    @InjectMocks
    private VoitureServiceImpl voitureService;
    @Mock
    private VoitureRepository voitureRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void testFindVoitureByPriceInf() {
        // liste de voitures simulée
        List<Voiture> voitures = new ArrayList<>();
        voitures.add(new Voiture(1, 2009, 38063, "Suburban 1500", "HFR-943", 1643.0,false));
        voitures.add(new Voiture(2, 2004, 41882, "Escalade ESV", "EFE-341", 368.0,false));
        when(voitureRepository.findAll()).thenReturn(voitures);
        List<Voiture> result = voitureService.findVoitureByPriceInf(1000.0); //prix inférieur à 1000.0
        // Vérifier le résultat
        assertEquals(1, result.size());
    }
    @Test
    void testFindVoitureByYear() {
        List<Voiture> voitures = new ArrayList<>();
        voitures.add(new Voiture(1, 2020, 38063, "Suburban 1500", "HFR-943", 1643.0,false));
        voitures.add(new Voiture(2, 2004, 41882, "Escalade ESV", "EFE-341", 368.0,false));
        when(voitureRepository.findAll()).thenReturn(voitures);
        List<Voiture> result = voitureService.findVoitureByYear(2020);
        assertEquals(1, result.size());
    }
    @Test
    void testFindVoitureByMileage() {
        List<Voiture> voitures = new ArrayList<>();
        voitures.add(new Voiture(1, 2009, 38063, "Suburban 1500", "HFR-943", 1643.0,false));
        voitures.add(new Voiture(2, 2004, 41882, "Escalade ESV", "EFE-341", 368.0,false));
        when(voitureRepository.findAll()).thenReturn(voitures);
        voitures = voitureService.findVoitureByMileage(38063);
        assertNotNull(voitures);
        assertFalse(voitures.isEmpty());
    }
}