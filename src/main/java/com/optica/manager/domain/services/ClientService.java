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

    @Autowired
    private TenantService tenantService;

    @Transactional(readOnly = true)
    public Page<ClientResponse> findByNameContainingIgnoreCase(String name, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        Integer unitId = tenantService.getUnitId();
        var pageClient = clientRepository.findByNameContainingIgnoreCaseAndUnitId(name, pageRequest, unitId);
        return pageClient.map(c -> ClientMapper.toClientResponseDTO(c));
    }

    @Transactional(readOnly = true)
    public ClientResponse getById(long id) {
        var unitId = tenantService.getUnitId();
        var client = clientRepository.findByIdAndUnitId(id, unitId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        return ClientMapper.toClientResponseDTO(client);
    }

    @Transactional
    public ClientResponse save(ClientRequest clientRequest) {
        var client = ClientMapper.fromClientRequestDTO(clientRequest);
        client.setUnit(tenantService.getUnitReference());
        client = clientRepository.save(client);
        return ClientMapper.toClientResponseDTO(client);
    }

    @Transactional
    public void update(long id, ClientRequest clientRequest) {
        var unitId = tenantService.getUnitId();
        var client = clientRepository.findByIdAndUnitId(id, unitId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        client.setCpf(clientRequest.cpf());
        client.setName(clientRequest.name());
        client.setPhone(clientRequest.phone());
    }

    @Transactional
    public void deleteById(long id) {

        Integer unitId = tenantService.getUnitId();

        if (!clientRepository.existsByIdAndUnitId(id, unitId)) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }

        try {
            clientRepository.deleteByIdAndUnitId(id, unitId);
            clientRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Conflito ao remover o cliente.");
        }
    }

}
