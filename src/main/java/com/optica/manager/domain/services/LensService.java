package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.LensMapper;
import com.optica.manager.domain.repositories.LensRepository;
import com.optica.manager.domain.services.exceptions.DatabaseException;
import com.optica.manager.dto.LensRequest;
import com.optica.manager.dto.LensResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LensService {
    
    @Autowired
    private LensRepository lensRepository;

    @Transactional(readOnly = true)
    public List<LensResponse> getLenses() {
        var lenses = lensRepository.findAll();
        return lenses.stream().map(l -> LensMapper.toLensResponseDTO(l)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LensResponse getById(long id) {
        var lens = lensRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lente não encontrada."));
        return LensMapper.toLensResponseDTO(lens);
    }

    @Transactional
    public LensResponse save(LensRequest lensRequest) {
        var lens = lensRepository.save(LensMapper.fromLensRequestDTO(lensRequest));
        return LensMapper.toLensResponseDTO(lens);
    }

    @Transactional
    public void update(long id, LensRequest lensRequest) {
        try {
            var lens = lensRepository.getReferenceById(id);


            lens.setCode(lensRequest.code());
            lens.setName(lensRequest.name());
            lens.setCostPrice(lensRequest.costPrice());
            lens.setSalePrice(lensRequest.salePrice());
            lens.setLensType(lensRequest.lensType());

            lensRepository.save(lens);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Lente não encontrada.");
        }
    }

    @Transactional
    public void deleteById(long id) {
        try {
            if (lensRepository.existsById(id)){
                lensRepository.deleteById(id);
            }
            else {
                throw new EntityNotFoundException("Lente não encontrada.");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Conflito ao remover a Lente.");
        }
    }

}
