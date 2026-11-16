package com.zakaria.muskutracker.dao;

import java.sql.*;

public class Database {
    public static final String URL = "jdbc:sqlite:muskutracker.db";

    public static Connection getConnection() throws Exception{
        Connection connection = DriverManager.getConnection(URL);

        try {
            Statement commande = connection.createStatement();
            commande.execute("PRAGMA foreign_keys = ON;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void initialiser() throws Exception {
        Connection connection = getConnection();

        String commandeExercice = "CREATE TABLE IF NOT EXISTS exercice ( id INTEGER PRIMARY KEY AUTOINCREMENT, wger_id INTEGER UNIQUE, nom TEXT NOT NULL," +
                "categorie TEXT NOT NULL, muscles TEXT NOT NULL);";

        String commandeSeance = "CREATE TABLE IF NOT EXISTS seance (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT NOT NULL, note TEXT);";

        String commandeSerie = "CREATE TABLE IF NOT EXISTS serie (id INTEGER PRIMARY KEY AUTOINCREMENT, seance_id INTEGER NOT NULL," +
                "exercice_id INTEGER NOT NULL, ordre INTEGER NOT NULL, reps INTEGER NOT NULL CHECK (reps >= 1 AND reps <= 100)," +
                "charge_kg REAL NOT NULL CHECK (charge_kg >= 0), FOREIGN KEY (seance_id) REFERENCES seance (id) ON DELETE CASCADE," +
                "FOREIGN KEY (exercice_id) REFERENCES exercice (id)" +
                ");";

        try(Statement commande = connection.createStatement();){
            commande.execute(commandeExercice);
            commande.execute(commandeSeance);
            commande.execute(commandeSerie);
            System.out.println("Base de donnee prete !");
        } catch (SQLException e) {
            System.out.println("Probleme avec les commandes");
            throw new RuntimeException(e);
        }
    }

}
