package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private TenantService tenantService;

    @Transactional(readOnly = true)
    public Page<LensResponse> findByNameContainingIgnoreCase(String name, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        var unitId = tenantService.getUnitId();
        var pageLens = lensRepository.findByNameContainingIgnoreCaseAndUnitId(name, pageRequest, unitId);
        return pageLens.map(l -> LensMapper.toLensResponseDTO(l));
    }

    @Transactional(readOnly = true)
    public List<LensResponse> getLenses() {
        var unitId = tenantService.getUnitId();
        var lenses = lensRepository.findAllByUnitId(unitId);
        return lenses.stream().map(l -> LensMapper.toLensResponseDTO(l)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LensResponse getById(long id) {
        var unitId = tenantService.getUnitId();
        var lens = lensRepository.findByIdAndUnitId(id, unitId)
                .orElseThrow(() -> new EntityNotFoundException("Lente não encontrada."));
        return LensMapper.toLensResponseDTO(lens);
    }

    @Transactional
    public LensResponse save(LensRequest lensRequest) {
        var lens = LensMapper.fromLensRequestDTO(lensRequest);
        lens.setUnit(tenantService.getUnitReference());
        lensRepository.save(lens);
        return LensMapper.toLensResponseDTO(lens);
    }

    @Transactional
    public void update(long id, LensRequest lensRequest) {

        var unitId = tenantService.getUnitId();
        var lens = lensRepository.findByIdAndUnitId(id, unitId)
                .orElseThrow(() -> new EntityNotFoundException("Lente não encontrada"));

        lens.setCode(lensRequest.code());
        lens.setName(lensRequest.name());
        lens.setBrand(lensRequest.brand());
        lens.setType(lensRequest.type());
        lens.setIndex(lensRequest.index());
        lens.setMaterial(lensRequest.material());
        lens.setTreatments(lensRequest.treatments());
    }

    @Transactional
    public void deleteById(long id) {
        var unitId = tenantService.getUnitId();
        if (!lensRepository.existsByIdAndUnitId(id, unitId)) {
            throw new EntityNotFoundException("Lente não encontrada.");
        }

        try {
            lensRepository.deleteByIdAndUnitId(id, unitId);
            lensRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Conflito ao remover a Lente.");
        }
    }

}
