package com.example.projetfinal.service;

import com.example.projetfinal.entity.Client;
import com.example.projetfinal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findClientById(int id) {
        return null;
    }

    @Override
    public boolean deleteClientById(int id) {
        return false;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
