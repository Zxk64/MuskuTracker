package com.zakaria.muskutracker;
import com.zakaria.muskutracker.dao.Database;
import com.zakaria.muskutracker.dao.ExerciceDao;
import com.zakaria.muskutracker.model.Exercice;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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
}