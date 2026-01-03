package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Client;
import com.optica.manager.dto.request.ClientRequest;
import com.optica.manager.dto.response.ClientResponse;

public class ClientMapper {

    public static ClientResponse toClientResponseDTO(Client client) {

        ClientResponse clientResponse = new ClientResponse(
                client.getId(),
                client.getCpf(),
                client.getName(),
                client.getPhone());
        return clientResponse;
    }

    public static Client fromClientRequestDTO(ClientRequest clientRequest) {
        return new Client(
                clientRequest.cpf(),
                clientRequest.name(),
                clientRequest.phone());
    }
}
