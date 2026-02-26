package com.optica.manager.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.ClientMapper;
import com.optica.manager.domain.repositories.ClientRepository;
import com.optica.manager.domain.services.exceptions.DatabaseException;
import com.optica.manager.dto.ClientRequest;
import com.optica.manager.dto.ClientResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientResponse> findByNameContainingIgnoreCase(String name, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        var pageClient = clientRepository.findByNameContainingIgnoreCase(name, pageRequest);
        return pageClient.map(c -> ClientMapper.toClientResponseDTO(c));
    }

    @Transactional(readOnly = true)
    public ClientResponse getById(long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        return ClientMapper.toClientResponseDTO(client);
    }

    @Transactional
    public ClientResponse save(ClientRequest clientRequest) {
        var client = clientRepository.save(ClientMapper.fromClientRequestDTO(clientRequest));
        return ClientMapper.toClientResponseDTO(client);
    }

    @Transactional
    public void update(long id, ClientRequest clientRequest) {
        try {
            var client = clientRepository.getReferenceById(id);

            client.setCpf(clientRequest.cpf());
            client.setName(clientRequest.name());
            client.setPhone(clientRequest.phone());

            clientRepository.save(client);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @Transactional
    public void deleteById(long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }

        try {
            clientRepository.deleteById(id);
            clientRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Conflito ao remover o cliente.");
        }
    }

}
