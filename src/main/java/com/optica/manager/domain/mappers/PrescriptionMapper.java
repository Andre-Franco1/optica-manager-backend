package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Prescription;
import com.optica.manager.dto.request.PrescriptionRequest;
import com.optica.manager.dto.response.PrescriptionResponse;

public class PrescriptionMapper {

    public static PrescriptionResponse toPrescriptionResponseDTO(Prescription prescription) {

        PrescriptionResponse prescriptionResponse = new PrescriptionResponse(
                prescription.getId(),
                prescription.getDate(),
                prescription.getDistanceOdSpherical(),
                prescription.getDistanceOdCylindrical(),
                prescription.getDistanceOdAxis(),
                prescription.getDistanceOdDnp(),
                prescription.getDistanceOdAddition(),
                prescription.getDistanceOdDp(),
                prescription.getDistanceOsSpherical(),
                prescription.getDistanceOsCylindrical(),
                prescription.getDistanceOsAxis(),
                prescription.getDistanceOsDnp(),
                prescription.getDistanceOsAddition(),
                prescription.getDistanceOsDp(),
                prescription.getNearOdSpherical(),
                prescription.getNearOdCylindrical(),
                prescription.getNearOdAxis(),
                prescription.getNearOdDnp(),
                prescription.getNearOdHeight(),
                prescription.getNearOdDp(),
                prescription.getNearOsSpherical(),
                prescription.getNearOsCylindrical(),
                prescription.getNearOsAxis(),
                prescription.getNearOsDnp(),
                prescription.getNearOsHeight(),
                prescription.getNearOsDp(),
                prescription.getOphthalmologist().getId(),
                prescription.getOphthalmologist().getName(),
                prescription.getClient().getId(),
                prescription.getNotes()
                );
        return prescriptionResponse;
    }

    public static Prescription fromPrescriptionRequestDTO(PrescriptionRequest prescriptionRequest) {
        return new Prescription(
                prescriptionRequest.date(),
                prescriptionRequest.distanceOdSpherical(),
                prescriptionRequest.distanceOdCylindrical(),
                prescriptionRequest.distanceOdAxis(),
                prescriptionRequest.distanceOdDnp(),
                prescriptionRequest.distanceOdAddition(),
                prescriptionRequest.distanceOdDp(),
                prescriptionRequest.distanceOsSpherical(),
                prescriptionRequest.distanceOsCylindrical(),
                prescriptionRequest.distanceOsAxis(),
                prescriptionRequest.distanceOsDnp(),
                prescriptionRequest.distanceOsAddition(),
                prescriptionRequest.distanceOsDp(),
                prescriptionRequest.nearOdSpherical(),
                prescriptionRequest.nearOdCylindrical(),
                prescriptionRequest.nearOdAxis(),
                prescriptionRequest.nearOdDnp(),
                prescriptionRequest.nearOdHeight(),
                prescriptionRequest.nearOdDp(),
                prescriptionRequest.nearOsSpherical(),
                prescriptionRequest.nearOsCylindrical(),
                prescriptionRequest.nearOsAxis(),
                prescriptionRequest.nearOsDnp(),
                prescriptionRequest.nearOsHeight(),
                prescriptionRequest.nearOsDp(),
                prescriptionRequest.notes());

    }
}
