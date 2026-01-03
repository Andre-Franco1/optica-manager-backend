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

import com.optica.manager.domain.services.PrescriptionService;
import com.optica.manager.dto.request.PrescriptionRequest;
import com.optica.manager.dto.response.PrescriptionResponse;

@RestController
@RequestMapping("/clients/{clientId}/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<PrescriptionResponse> findAllByClient(@PathVariable Long clientId){
        return prescriptionService.findAllByClient(clientId);
    }

    @GetMapping("{id}")
    public ResponseEntity<PrescriptionResponse> getPrescription(@PathVariable long id) {
        var prescription = prescriptionService.getById(id);
        return ResponseEntity.ok(prescription);
    }

    @PostMapping
    public ResponseEntity<PrescriptionResponse> savePrescription(@PathVariable long clientId, @Validated @RequestBody PrescriptionRequest prescriptionRequest) {
        var prescriptionResponse = prescriptionService.save(clientId, prescriptionRequest);

        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(prescriptionResponse.id()).toUri();

        return ResponseEntity.created(location).body(prescriptionResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePrescription(@PathVariable long clientId, @PathVariable long id,
            @Validated @RequestBody PrescriptionRequest prescriptionRequest) {
        prescriptionService.update(clientId, id, prescriptionRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable long clientId, @PathVariable long id) {
        prescriptionService.deleteById(clientId, id);
        return ResponseEntity.noContent().build();
    }
    
}
