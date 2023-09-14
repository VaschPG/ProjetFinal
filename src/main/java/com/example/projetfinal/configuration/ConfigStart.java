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

import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        creerClientsEtReservationsEtVoiture();
        //creerReservationsAvecVoitureEtClient(clientRepository);
        }
    }

    private void creerReservationsAvecVoitureEtClient(ClientRepository clientRepository){


        Client client1 = new Client();
        client1.setNom("Bob");
        client1.setPrenom("George");
        client1.setAdresse("1000 rue");
        client1.setTelephone("111-222-4444");

        Voiture voiture1 = new Voiture(1, 2020, 38063, "Suburban 1500", "HFR-943", 1643.0);
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

        client1.add(reservation1);

        clientRepository.save(client1);
        clientRepository.save(client2);


    }

    public void creerClientsEtReservationsEtVoiture() {
        String ligne = "";
        String splitBy = ",";
        List<Client> listClient = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/clients.csv"));
            br.readLine(); //Ignorer la premier ligne
            while ((ligne = br.readLine()) != null)
            {
                String[] client = ligne.split(splitBy);
                Client newClient = new Client();
                newClient.setNom(client[0]);
                newClient.setPrenom(client[1]);
                newClient.setTelephone(client[2]);
                newClient.setAdresse(client[3]);
                listClient.add(newClient);
            }

            creerReservations(listClient,creerVoitures());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<Voiture> creerVoitures() {
        String ligne = "";
        String splitBy = ",";
        List<Voiture> listVoiture = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/voitures.csv"));
            br.readLine(); //Ignorer la premier ligne
            while ((ligne = br.readLine()) != null)
            {
                String[] voiture = ligne.split(splitBy);
                Voiture newVoiture = new Voiture(1, 2020, 38063, "Suburban 1500", "HFR-943", 1643.0);
                newVoiture.setYear(Integer.parseInt(voiture[0]));
                newVoiture.setMileage(Integer.parseInt(voiture[1]));
                newVoiture.setModel(voiture[2]);
                newVoiture.setLicense(voiture[3]);
                newVoiture.setPrice(Double.parseDouble(voiture[4]));
                listVoiture.add(newVoiture);
            }
            return listVoiture;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void creerReservations(List<Client> listClient, List<Voiture> listVoiture) {
        String ligne = "";
        String splitBy = ",";
        int i = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/reservations.csv"));
            br.readLine(); //Ignorer la premier ligne
            while ((ligne = br.readLine()) != null)
            {
                String[] reservation = ligne.split(splitBy);
                if(!reservation[0].equals("")){
                    Reservation newReservation = new Reservation();
                    Date date = new SimpleDateFormat("dd/mm/yyyy").parse(reservation[1]);
                    newReservation.setDate(date);
                    newReservation.setEmploye(reservation[2]);
                    newReservation.setVoiture(listVoiture.get(Integer.parseInt(reservation[3])));
                    listClient.get(i).add(newReservation);
                }
                clientRepository.save(listClient.get(i));
                i++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
