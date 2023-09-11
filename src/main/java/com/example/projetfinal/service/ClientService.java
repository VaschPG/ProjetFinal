package com.example.projetfinal.service;

import com.example.projetfinal.entity.Client;

import java.util.List;

public interface ClientService {
    Client saveClient(Client client);
    Client findClientById(int id);
    void deleteClientById(int id);

    List<Client> findAllClients();

    List<Client> findClientsByParams(String nom,String telephone,String adresse);
}
