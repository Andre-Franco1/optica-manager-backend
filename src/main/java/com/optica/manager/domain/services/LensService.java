package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.LensMapper;
import com.optica.manager.domain.repositories.LensRepository;
import com.optica.manager.dto.LensResponse;

@Service
public class LensService {
    
    @Autowired
    private LensRepository lensRepository;

    @Transactional(readOnly = true)
    public List<LensResponse> getLenses() {
        var lenses = lensRepository.findAll();
        return lenses.stream().map(l -> LensMapper.toLensResponseDTO(l)).collect(Collectors.toList());
    }

}
