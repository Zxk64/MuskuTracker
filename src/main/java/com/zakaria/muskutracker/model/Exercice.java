package com.zakaria.muskutracker.model;

public class Exercice {

    private int id;
    private int wgerId;
    private String nom;
    private String categorie;
    private String muscles;
    

    public Exercice(int id, int wgerId, String nom, String categorie, String muscles){
        this.id = id;
        this.wgerId = wgerId;
        this.nom = nom;
        this.categorie = categorie;
        this.muscles = muscles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWgerId() {
        return wgerId;
    }

    public void setWgerId(int wgerId) {
        this.wgerId = wgerId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getMuscles() {
        return muscles;
    }

    public void setMuscles(String muscles) {
        this.muscles = muscles;
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "id=" + id +
                ", wgerId=" + wgerId +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", muscles='" + muscles + '\'' +
                '}';
    }
}
