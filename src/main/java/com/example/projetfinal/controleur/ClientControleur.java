package com.example.projetfinal.controleur;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.service.ClientService;
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
    public ClientControleur(ClientService clientService){
        this.clientService = clientService;;
    }
    @GetMapping("/gestion-client")
    public String indexClient(Model model){
        List<Client> listClients = clientService.findAllClients();
        model.addAttribute("client",new Client());
        model.addAttribute("listClients", listClients);
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

    @PostMapping("/client")
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @DeleteMapping(path="/client/delete/{id}")
    public void deleteClientById(@PathVariable("id") int id) throws Exception {
        clientService.deleteClientById(id);
    }
}
