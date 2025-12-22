package com.optica.manager.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.manager.domain.services.FrameService;
import com.optica.manager.dto.FrameResponse;

@RestController
@RequestMapping("frames")
public class FrameController {

    @Autowired
    private FrameService frameService;

    @GetMapping
    public ResponseEntity<List<FrameResponse>> getFrames() {
        var frames = frameService.getFrames();
        return ResponseEntity.ok(frames);
    }
    
}
