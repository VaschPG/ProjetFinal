package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.repository.ClientRepository;
import com.example.projetfinal.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientControleur {
    @Autowired
    private ClientService clientService;
    public ClientControleur(ClientService clientService){
        this.clientService = clientService;;
    }

    @GetMapping("/")
    public String SayHello(){return "Hello";}

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
    public List<Client> getAllClients() { return clientService.findAll();}

    @PostMapping("/clients")
    public Client saveClient(@RequestBody Client client) {

        return clientService.saveClient(client);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id") int id) throws Exception {

        boolean deleted = clientService.deleteClientById(id);

        if(deleted) {
            return ResponseEntity.ok("Client avec Id " + id + " a ete supprimer");
        }else{
            return ResponseEntity.badRequest().body("Client avec id " + id + " non existant");
        }
    }
}
