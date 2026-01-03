package com.optica.manager.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optica.manager.domain.mappers.PrescriptionMapper;
import com.optica.manager.domain.repositories.ClientRepository;
import com.optica.manager.domain.repositories.OphthalmologistRepository;
import com.optica.manager.domain.repositories.PrescriptionRepository;
import com.optica.manager.domain.services.exceptions.DatabaseException;
import com.optica.manager.dto.request.PrescriptionRequest;
import com.optica.manager.dto.response.PrescriptionResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OphthalmologistRepository ophthalmologistRepository;

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

        var ophthalmologist = ophthalmologistRepository.findById(prescriptionRequest.ophthalmologistId())
                .orElseThrow(() -> new EntityNotFoundException("Oftalmologista não encontrado."));

        var prescription = PrescriptionMapper.fromPrescriptionRequestDTO(prescriptionRequest);
        prescription.setClient(client);
        prescription.setOphthalmologist(ophthalmologist);

        var savedPrescription = prescriptionRepository.save(prescription);
        return PrescriptionMapper.toPrescriptionResponseDTO(savedPrescription);
    }

    @Transactional
    public void update(long clientId, long id, PrescriptionRequest prescriptionRequest) {

        var prescription = prescriptionRepository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new EntityNotFoundException("Receita não encontrada."));

        prescription.setDate(prescriptionRequest.date());
        prescription.setDistanceOdSpherical(prescriptionRequest.distanceOdSpherical());
        prescription.setDistanceOdCylindrical(prescriptionRequest.distanceOdCylindrical());
        prescription.setDistanceOdAxis(prescriptionRequest.distanceOdAxis());
        prescription.setDistanceOdDnp(prescriptionRequest.distanceOdDnp());
        prescription.setDistanceOdAddition(prescriptionRequest.distanceOdAddition());
        prescription.setDistanceOdDp(prescriptionRequest.distanceOdDp());
        prescription.setDistanceOsSpherical(prescriptionRequest.distanceOsSpherical());
        prescription.setDistanceOsCylindrical(prescriptionRequest.distanceOsCylindrical());
        prescription.setDistanceOsAxis(prescriptionRequest.distanceOsAxis());
        prescription.setDistanceOsDnp(prescriptionRequest.distanceOsDnp());
        prescription.setDistanceOsAddition(prescriptionRequest.distanceOsAddition());
        prescription.setDistanceOsDp(prescriptionRequest.distanceOsDp());
        prescription.setNearOdSpherical(prescriptionRequest.nearOdSpherical());
        prescription.setNearOdCylindrical(prescriptionRequest.nearOdCylindrical());
        prescription.setNearOdAxis(prescriptionRequest.nearOdAxis());
        prescription.setNearOdDnp(prescriptionRequest.nearOdDnp());
        prescription.setNearOdHeight(prescriptionRequest.nearOdHeight());
        prescription.setNearOdDp(prescriptionRequest.nearOdDp());
        prescription.setNearOsSpherical(prescriptionRequest.nearOsSpherical());
        prescription.setNearOsCylindrical(prescriptionRequest.nearOsCylindrical());
        prescription.setNearOsAxis(prescriptionRequest.nearOsAxis());
        prescription.setNearOsDnp(prescriptionRequest.nearOsDnp());
        prescription.setNearOsHeight(prescriptionRequest.nearOsHeight());
        prescription.setNearOsDp(prescriptionRequest.nearOsDp());
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
