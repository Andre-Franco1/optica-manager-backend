package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.FrameMapper;
import com.optica.manager.domain.repositories.FrameRepository;
import com.optica.manager.domain.services.exceptions.DatabaseException;
import com.optica.manager.dto.FrameRequest;
import com.optica.manager.dto.FrameResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FrameService {
    
    @Autowired
    private FrameRepository frameRepository;

    @Transactional(readOnly = true)
    public Page<FrameResponse> findByNameContainingIgnoreCase(String name, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        var pageFrame = frameRepository.findByNameContainingIgnoreCase(name, pageRequest);
        return pageFrame.map(f -> FrameMapper.toFrameResponseDTO(f));
    }

    @Transactional(readOnly = true)
    public List<FrameResponse> getFrames() {
        var frames = frameRepository.findAll();
        return frames.stream().map(f -> FrameMapper.toFrameResponseDTO(f)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FrameResponse getById(long id) {
        var frame = frameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Armação não encontrada."));
        return FrameMapper.toFrameResponseDTO(frame);
    }

    @Transactional
    public FrameResponse save(FrameRequest frameRequest) {
        var frame = frameRepository.save(FrameMapper.fromFrameRequestDTO(frameRequest));
        frame.setStockQuantity(0);
        return FrameMapper.toFrameResponseDTO(frame);
    }

    @Transactional
    public void update(long id, FrameRequest frameRequest) {
        try {
            var frame = frameRepository.getReferenceById(id);


            frame.setCode(frameRequest.code());
            frame.setName(frameRequest.name());
            frame.setBrand(frameRequest.brand());
            frame.setType(frameRequest.type());

            frameRepository.save(frame);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Armação não encontrada.");
        }
    }

    @Transactional
    public void deleteById(long id) {
        if (!frameRepository.existsById(id)) {
            throw new EntityNotFoundException("Armação não encontrada.");
        }

        try {
            frameRepository.deleteById(id);
            frameRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Conflito ao remover a armação.");
        }
    }

}
