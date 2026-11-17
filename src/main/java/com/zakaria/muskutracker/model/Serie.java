package com.zakaria.muskutracker.model;

public class Serie {

    private int id;
    private int seanceId;
    private int exerciceId;
    private int ordre;
    private int reps;
    private double chargeKg;

    public Serie(int seanceId, int exerciceId, int ordre, int reps, double chargeKg) {
        this.seanceId = seanceId;
        this.exerciceId = exerciceId;
        this.ordre = ordre;
        this.reps = reps;
        this.chargeKg = chargeKg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(int seanceId) {
        this.seanceId = seanceId;
    }

    public int getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(int exerciceId) {
        this.exerciceId = exerciceId;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getChargeKg() {
        return chargeKg;
    }

    public void setChargeKg(double chargeKg) {
        this.chargeKg = chargeKg;
    }

    @Override
    public String toString() {
        return "\nSerie{" +
                "id=" + id +
                ", seanceId=" + seanceId +
                ", exerciceId=" + exerciceId +
                ", ordre=" + ordre +
                ", reps=" + reps +
                ", chargeKg=" + chargeKg +
                '}';
    }
}
