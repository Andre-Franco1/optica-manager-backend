package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Prescription;
import com.optica.manager.dto.PrescriptionRequest;
import com.optica.manager.dto.PrescriptionResponse;

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
                prescription.getDistanceOsSpherical(),
                prescription.getDistanceOsCylindrical(),
                prescription.getDistanceOsAxis(),
                prescription.getDistanceOsDnp(),
                prescription.getDistanceOsAddition(),
                prescription.getDistanceDp(),
                prescription.getNearOdSpherical(),
                prescription.getNearOdCylindrical(),
                prescription.getNearOdAxis(),
                prescription.getNearOdDnp(),
                prescription.getNearOdHeight(),
                prescription.getNearOsSpherical(),
                prescription.getNearOsCylindrical(),
                prescription.getNearOsAxis(),
                prescription.getNearOsDnp(),
                prescription.getNearOsHeight(),
                prescription.getNearDp(),
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
                prescriptionRequest.distanceOsSpherical(),
                prescriptionRequest.distanceOsCylindrical(),
                prescriptionRequest.distanceOsAxis(),
                prescriptionRequest.distanceOsDnp(),
                prescriptionRequest.distanceOsAddition(),
                prescriptionRequest.distanceDp(),
                prescriptionRequest.nearOdSpherical(),
                prescriptionRequest.nearOdCylindrical(),
                prescriptionRequest.nearOdAxis(),
                prescriptionRequest.nearOdDnp(),
                prescriptionRequest.nearOdHeight(),
                prescriptionRequest.nearOsSpherical(),
                prescriptionRequest.nearOsCylindrical(),
                prescriptionRequest.nearOsAxis(),
                prescriptionRequest.nearOsDnp(),
                prescriptionRequest.nearOsHeight(),
                prescriptionRequest.nearDp(),
                prescriptionRequest.notes());

    }
}
