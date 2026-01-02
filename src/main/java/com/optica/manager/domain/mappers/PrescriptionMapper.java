package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Prescription;
import com.optica.manager.dto.PrescriptionRequest;
import com.optica.manager.dto.PrescriptionResponse;

public class PrescriptionMapper {

    public static PrescriptionResponse toPrescriptionResponseDTO(Prescription prescription) {

        PrescriptionResponse prescriptionResponse = new PrescriptionResponse(
                prescription.getId(),
                prescription.getDate(),
                prescription.getOd_sphere(),
                prescription.getOd_cylinder(),
                prescription.getOd_axis(),
                prescription.getOs_sphere(),
                prescription.getOs_cylinder(),
                prescription.getOs_axis(),
                prescription.getAddition(),
                prescription.getNotes(),
                prescription.getClient().getId());
        return prescriptionResponse;
    }

    public static Prescription fromPrescriptionRequestDTO(PrescriptionRequest prescriptionRequest) {
        return new Prescription(
            prescriptionRequest.date(),
            prescriptionRequest.od_sphere(),
            prescriptionRequest.od_cylinder(),
            prescriptionRequest.od_axis(),
            prescriptionRequest.os_sphere(),
            prescriptionRequest.os_cylinder(),
            prescriptionRequest.os_axis(),
            prescriptionRequest.addition(),
            prescriptionRequest.notes());
    }
}
