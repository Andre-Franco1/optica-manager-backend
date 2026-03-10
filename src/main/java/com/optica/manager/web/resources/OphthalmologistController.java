package com.optica.manager.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.manager.domain.services.OphthalmologistService;
import com.optica.manager.dto.OphthalmologistResponse;

@RestController
@RequestMapping("ophthalmologists")
public class OphthalmologistController {

    @Autowired
    private OphthalmologistService ophthalmologistService;

    @GetMapping
    public List<OphthalmologistResponse> findAll(){
        return ophthalmologistService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<OphthalmologistResponse> getOphthalmologist(@PathVariable int id) {
        var ophthalmologist = ophthalmologistService.getById(id);
        return ResponseEntity.ok(ophthalmologist);
    }

    

}
