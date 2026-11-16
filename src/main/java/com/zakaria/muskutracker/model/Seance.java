package com.zakaria.muskutracker.model;

import java.time.LocalDate;

public class Seance {

    private int id;
    private LocalDate date;
    private String note;

    public Seance(int id, LocalDate date, String note) {
        this.id = id;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", date=" + date +
                ", note='" + note + '\'' +
                '}';
    }
}
