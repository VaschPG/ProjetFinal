package com.example.projetfinal.configuration;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.entity.Reservation;
import com.example.projetfinal.entity.Voiture;
import com.example.projetfinal.repository.ClientRepository;
import com.example.projetfinal.repository.ReservationRepository;
import com.example.projetfinal.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ConfigStart  implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VoitureRepository voitureRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public ConfigStart(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        if(clientRepository.findAll().isEmpty() && voitureRepository.findAll().isEmpty()
        && reservationRepository.findAll().isEmpty()){

        creerReservationsAvecVoitureEtClient(clientRepository);
        }
    }

    private void creerReservationsAvecVoitureEtClient(ClientRepository clientRepository){
        Client client1 = new Client();
        client1.setNom("Bob");
        client1.setPrenom("George");
        client1.setAdresse("1000 rue");
        client1.setTelephone("111-222-4444");

        Voiture voiture1 = new Voiture();
        voiture1.setYear(2011);
        voiture1.setMileage(20000);
        voiture1.setModel("Toyota");
        voiture1.setLicense("ZAB-324");
        voiture1.setPrice(1000);

        Reservation reservation1 = new Reservation();
        Date date1 = new Date("11/11/2023");
        reservation1.setDate(date1);
        reservation1.setEmploye("David");
        reservation1.setVoiture(voiture1);

        Client client2 = new Client();
        client2.setNom("Bob2");
        client2.setPrenom("George2");
        client2.setAdresse("1000 rue2");
        client2.setTelephone("222-222-4444");

        Client client3 = new Client();
        client3.setNom("eh");
        client3.setPrenom("bluh");
        client3.setAdresse("5000 ru");
        client3.setTelephone("111-111-1111");


        client1.add(reservation1);
        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
    }
}
