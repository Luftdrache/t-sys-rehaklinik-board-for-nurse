package com.tsystems.rehaklinik.board.data;

import com.tsystems.rehaklinik.board.data.types.EventStatus;
import com.tsystems.rehaklinik.board.data.types.TreatmentType;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreatmentEvent implements Serializable {

    private String patient;
    private String hospitalDepartment;
    private String hospitalWard;
    private String treatmentEventDate;
    private String treatmentEventTime;
    private String medicineProcedureName;
    private TreatmentType treatmentType;
    private String dose;
    private String administeringMedicationMethod;
    private String cancelReason;
    private EventStatus treatmentEventStatus;
    private String treatmentPeriodStartDate;
    private String treatmentPeriodEndDate;

}
