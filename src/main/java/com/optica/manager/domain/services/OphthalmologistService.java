package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.OphthalmologistMapper;
import com.optica.manager.domain.repositories.OphthalmologistRepository;
import com.optica.manager.dto.OphthalmologistResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OphthalmologistService {

    @Autowired
    private OphthalmologistRepository ophthalmologistRepository;

    @Transactional(readOnly = true)
    public List<OphthalmologistResponse> findAll() {
        var ophthalmologists = ophthalmologistRepository.findAll();
        return ophthalmologists.stream().map(o -> OphthalmologistMapper.toOphthalmologistResponseDTO(o))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OphthalmologistResponse getById(int id) {
        var ophthalmologists = ophthalmologistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Oftalmologista não encontrado"));
        return OphthalmologistMapper.toOphthalmologistResponseDTO(ophthalmologists);
    }

}
