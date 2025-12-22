package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.FrameMapper;
import com.optica.manager.domain.repositories.FrameRepository;
import com.optica.manager.dto.FrameResponse;

@Service
public class FrameService {
    
    @Autowired
    private FrameRepository frameRepository;

    @Transactional(readOnly = true)
    public List<FrameResponse> getFrames() {
        var frames = frameRepository.findAll();
        return frames.stream().map(f -> FrameMapper.toFrameResponseDTO(f)).collect(Collectors.toList());
    }

}
