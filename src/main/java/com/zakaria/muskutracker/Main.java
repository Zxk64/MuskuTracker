package com.zakaria.muskutracker;
import com.zakaria.muskutracker.dao.Database;
import com.zakaria.muskutracker.dao.ExerciceDao;
import com.zakaria.muskutracker.model.Exercice;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Database.initialiser();
            ExerciceDao test = new ExerciceDao();
            ArrayList<Exercice> exos = test.trouverTous();
            for (Exercice e : exos){
                System.out.println(e);
            }
            System.out.println();
            Exercice exo = test.trouverParId(3);
            System.out.println(exo);
            ArrayList<Exercice> test2 = test.chercherParNom("Squ");
            for (Exercice e : test2){
                System.out.println(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}