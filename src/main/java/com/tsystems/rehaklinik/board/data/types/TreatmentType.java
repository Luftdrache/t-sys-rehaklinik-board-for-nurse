package com.tsystems.rehaklinik.board.data.types;

public enum TreatmentType {
    MEDICINE("Medicine"),
    PROCEDURE("Procedure");

    private String treatmentType;

    TreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    @Override
    public String toString() {
        return treatmentType;
    }
}
