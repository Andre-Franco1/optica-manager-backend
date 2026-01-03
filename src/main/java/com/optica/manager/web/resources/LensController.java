package com.optica.manager.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.optica.manager.domain.services.LensService;
import com.optica.manager.dto.request.LensRequest;
import com.optica.manager.dto.response.LensResponse;

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

    @GetMapping("{id}")
    public ResponseEntity<LensResponse> getLens(@PathVariable long id) {
        var lens = lensService.getById(id);
        return ResponseEntity.ok(lens);
    }

    @PostMapping
    public ResponseEntity<LensResponse> saveLens(@Validated @RequestBody LensRequest lensRequest) {
        var lensResponse = lensService.save(lensRequest);

        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(lensResponse.id()).toUri();

        return ResponseEntity.created(location).body(lensResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateLens(@PathVariable long id,
            @Validated @RequestBody LensRequest lensRequest) {
        lensService.update(id, lensRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLens(@PathVariable long id) {
        lensService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
