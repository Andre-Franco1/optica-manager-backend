package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.PrescriptionMapper;
import com.optica.manager.domain.repositories.ClientRepository;
import com.optica.manager.domain.repositories.PrescriptionRepository;
import com.optica.manager.domain.services.exceptions.DatabaseException;
import com.optica.manager.dto.PrescriptionRequest;
import com.optica.manager.dto.PrescriptionResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<PrescriptionResponse> findAllByClient(Long clientId) {
        var prescriptions = prescriptionRepository.findAllByClientId(clientId);
        return prescriptions.stream().map(p -> PrescriptionMapper.toPrescriptionResponseDTO(p))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PrescriptionResponse getById(long id) {
        var prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Receita não encontrada."));
        return PrescriptionMapper.toPrescriptionResponseDTO(prescription);
    }

    @Transactional
    public PrescriptionResponse save(long clientId, PrescriptionRequest prescriptionRequest) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        var prescription = PrescriptionMapper.fromPrescriptionRequestDTO(prescriptionRequest);
        prescription.setClient(client);

        var savedPrescription = prescriptionRepository.save(prescription);
        return PrescriptionMapper.toPrescriptionResponseDTO(savedPrescription);
    }

    @Transactional
    public void update(long clientId, long id, PrescriptionRequest prescriptionRequest) {

        var prescription = prescriptionRepository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new EntityNotFoundException("Receita não encontrada."));

        prescription.setDate(prescriptionRequest.date());
        prescription.setOd_sphere(prescriptionRequest.od_sphere());
        prescription.setOd_cylinder(prescriptionRequest.od_cylinder());
        prescription.setOd_axis(prescriptionRequest.od_axis());
        prescription.setOs_sphere(prescriptionRequest.os_sphere());
        prescription.setOs_cylinder(prescriptionRequest.os_cylinder());
        prescription.setOs_axis(prescriptionRequest.os_axis());
        prescription.setAddition(prescriptionRequest.addition());
        prescription.setNotes(prescriptionRequest.notes());

        prescriptionRepository.save(prescription);
    }

    @Transactional
    public void deleteById(long clientId, long id) {
        try {
            if (prescriptionRepository.existsByIdAndClientId(id, clientId)){
                prescriptionRepository.deleteByIdAndClientId(id, clientId);
            }
            else {
                throw new EntityNotFoundException("Receita não encontrada.");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Conflito ao remover a receita.");
        }
    }   

}
