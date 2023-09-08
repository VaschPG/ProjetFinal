package com.example.projetfinal.service;

import com.example.projetfinal.entity.Client;

import java.util.List;

public interface ClientService {
    Client saveClient(Client client);
    Client findClientById(int id) throws Exception;
    boolean deleteClientById(int id);
    List<Client> findAll();
}
