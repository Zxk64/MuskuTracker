package com.zakaria.muskutracker.service;

import com.zakaria.muskutracker.dao.ExerciceDao;
import com.zakaria.muskutracker.dao.SeanceDao;
import com.zakaria.muskutracker.dao.SerieDao;
import com.zakaria.muskutracker.model.Exercice;
import com.zakaria.muskutracker.model.Seance;
import com.zakaria.muskutracker.model.Serie;

import java.time.LocalDate;
import java.util.List;

public class EntrainementService {

    private ExerciceDao exoD;
    private SeanceDao seaD;
    private SerieDao serD;

    final int LONGUEUR_MAX_NOTE = 200;

    public EntrainementService(ExerciceDao exoD, SeanceDao seaD, SerieDao serD) {
        this.exoD = exoD;
        this.seaD = seaD;
        this.serD = serD;
    }

    public Seance demarrerSeance(LocalDate date, String note){

        Seance s;
        if (note == null || note.isBlank()){
            note = "";
        }

        if (!date.isAfter(LocalDate.now()) && note.length() <= LONGUEUR_MAX_NOTE){
            s = seaD.ajouter(new Seance(date, note));
        } else {
            throw new ValidationException("La date est invalide ou la note est trop longue");
        }
        return s;
    }

    public Serie ajouterSerie(int idSeance, int idExercice, int reps, double chargeKg){
        Seance s = seaD.trouverParId(idSeance);
        Exercice e = exoD.trouverParId(idExercice);

        int ordre = serD.prochainOrdre(idSeance);

        if (s != null && e != null){
            if (reps >= 1 && reps <= 100 && chargeKg >= 0 && chargeKg <= 1000){
                return serD.ajouter(new  Serie(idSeance, idExercice, ordre, reps, chargeKg));
            } else {
                throw new ValidationException("Les reps ou la charge sont invalide !");
            }
        } else {
            throw new ValidationException("La seance ou l'exercice n'existe pas !");
        }
    }

    public List<Seance> historique(){
        return seaD.trouverToutes();
    }

    public List<Serie> detailSeance(int idSeance){
        Seance s = seaD.trouverParId(idSeance);
        if (s != null){
            return serD.trouverParSeance(idSeance);
        } throw new ValidationException("La seance n'existe pas !");
    }

    public void supprimerSeance(int idSeance){
        Seance s = seaD.trouverParId(idSeance);
        if (s != null){
            seaD.supprimer(idSeance);
        } else {
            throw new ValidationException("La seance n'existe pas !");
        }
    }

    public List<Exercice> exercicesDisponibles(){
        return exoD.trouverTous();
    }
}
