package com.optica.manager.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.manager.domain.services.LensService;
import com.optica.manager.dto.LensResponse;

@RestController
@RequestMapping("lenses")
public class LensController {

    @Autowired
    private LensService lensService;

    @GetMapping
    public ResponseEntity<List<LensResponse>> getLenses() {
        var lenses = lensService.getLenses();
        return ResponseEntity.ok(lenses);
    }
    
}
