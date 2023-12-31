package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.entity.Reservation;
import com.example.projetfinal.repository.ClientRepository;
import com.example.projetfinal.service.ClientService;
import com.example.projetfinal.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ClientControleur {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ClientRepository clientRepository;

    public ClientControleur(ClientService clientService){
        this.clientService = clientService;
    }
//    @GetMapping("/gestion-client")
//    public String indexClient(Model model){
//        List<Client> listClients = clientService.findAllClients();
//        model.addAttribute("client",new Client());
//        model.addAttribute("listClients", listClients);
//        return "gestion-client";
//    }
    @GetMapping("gestion-client")
    public String updatePageChercher(Model model,@ModelAttribute("client") Client client) {
        if(client.getNom() != null && client.getTelephone() != null || client.getAdresse() != null){
            List<Client> list = clientService.findClientsByParams(client.getNom(),client.getTelephone(),client.getAdresse());
            model.addAttribute("listClients",list);
            System.out.println("tests");
        }else{
            List<Client> listClients = clientService.findAllClients();
             model.addAttribute("client",new Client());
             model.addAttribute("listClients", listClients);
        }
        return "gestion-client";
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") int id) throws Exception {

        Client client = null;
        try{
            client = clientService.findClientById(id);
        }catch(Exception e){
            throw new Exception("Le client n'a pas ete trouve");
        }
        return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/clients")
    public ResponseEntity<List> getAllClients() { return
            new ResponseEntity<>(clientService.findAllClients(),HttpStatus.NOT_FOUND);}



    @DeleteMapping("/client/{id}")
    public String deleteClientById(@PathVariable("id") int id){
        clientService.deleteClientById(id);
        return "redirect:/gestion-client";
    }

    @GetMapping("/client-form")
    public String gestionClientForm(Model model){
        model.addAttribute("client",new Client());
        return "client-form";
    }

    @PostMapping("/client-form/save")
    public String saveClient(Model model,@ModelAttribute("client") Client client){
        if(!clientRepository.existsById(client.getId())){
            clientRepository.save(client);
        }else{
            Client clientExistant = clientRepository.getReferenceById(client.getId());
            clientExistant.setNom(client.getNom());
            clientExistant.setPrenom(client.getPrenom());
            clientExistant.setAdresse(client.getAdresse());
            clientExistant.setTelephone(client.getTelephone());
            clientRepository.save(clientExistant);
        }
        return "redirect:/gestion-client";
    }

    @GetMapping("gestion-client/{id}")
    public String getClientFormUpdate(@PathVariable("id") int id,Model model){
        model.addAttribute("client",clientService.findClientById(id));
        return "client-form";
    }
}
