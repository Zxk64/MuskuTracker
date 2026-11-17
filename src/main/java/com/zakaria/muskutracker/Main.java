package com.zakaria.muskutracker;
import com.zakaria.muskutracker.dao.Database;
import com.zakaria.muskutracker.dao.ExerciceDao;
import com.zakaria.muskutracker.dao.SeanceDao;
import com.zakaria.muskutracker.model.Exercice;
import com.zakaria.muskutracker.model.Seance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testerSeanceDAO();
    }

    static void testerExerciceDAO(){
        try {
            Database.initialiser();
            ExerciceDao t2 = new ExerciceDao();

            // TEST METHODE ajouter EXERCICE
            t2.ajouter(new Exercice(0, "Curl Biceps", "Bras", "Biceps"));
            t2.ajouter(new Exercice(1, "Develope Couche", "Poitrine", "Pectoraux, Triceps"));
            t2.ajouter(new Exercice(2, "Squat", "Jambes", "Ischio-Fessier"));
            t2.ajouter(new Exercice(3, "Traction", "Dos", "Dos, Biceps"));
            System.out.println("-----------");

            // TEST METHODE trouverTous EXERCICE
            List<Exercice> exos = t2.trouverTous();
            for (Exercice e : exos){
                System.out.println(e);
            }
            System.out.println("-----------");

            // TEST METHODE trouverParId EXERCICE
            System.out.println(t2.trouverParId(1));
            System.out.println("-----------");

            // TEST METHODE chercherParNom EXERCICE
            List<Exercice> exos2 = t2.chercherParNom("c");
            for (Exercice e : exos2){
                System.out.println(e);
            }
            System.out.println("-----------");

            // TEST METHODE compter EXERCICE
            System.out.println(t2.compter());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void testerSeanceDAO(){
        try{
            Database.initialiser();
            SeanceDao s2 = new SeanceDao();

            // TEST METHODE ajouter SEANCE
            Seance s = s2.ajouter(new Seance(LocalDate.parse("2025-11-16"), "Skip la salle mais seance maison"));
            System.out.println(s);
            System.out.println("-----------");

            // TEST METHODE trouverToutes SEANCE
            List<Seance> ss = s2.trouverToutes();
            for (Seance seance : ss){
                System.out.println(seance);
            }
            System.out.println("-----------");

            // TEST METHODE trouverParId SEANCE
            System.out.println(s2.trouverParId(3));
            System.out.println("-----------");

            // TEST METHODE supprimer SEANCE
            s2.supprimer(9);
            s2.supprimer(8);
            s2.supprimer(3);
            s2.supprimer(4);
            s2.supprimer(5);
            s2.supprimer(6);
            s2.supprimer(7);
            List<Seance> sss = s2.trouverToutes();
            for (Seance seance : sss){
                System.out.println(seance);
            }
            System.out.println("-----------");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}