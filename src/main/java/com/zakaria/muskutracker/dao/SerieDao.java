package com.zakaria.muskutracker.dao;

import com.zakaria.muskutracker.model.Exercice;
import com.zakaria.muskutracker.model.Seance;
import com.zakaria.muskutracker.model.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieDao {

    private Serie lireSerie(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");

        int seanceId = rs.getInt("seance_id");
        if (rs.wasNull()){
            seanceId = -1;
        }

        int exerciceId = rs.getInt("exercice_id");
        if(rs.wasNull()){
            exerciceId = -1;
        }

        int ordre = rs.getInt("ordre");
        int reps = rs.getInt("reps");
        double chargeKg = rs.getDouble("charge_kg");

        Serie nouvelleSerie = new Serie(seanceId, exerciceId, ordre, reps, chargeKg);
        nouvelleSerie.setId(id);

        return nouvelleSerie;
    }

    public Serie ajouter(Serie ser) {

        // Preparation de la commande
        String comm = "INSERT INTO serie (seance_id, exercice_id, ordre, reps, charge_kg) VALUES (?, ?, ?, ?, ?);";
        String comm2 = "SELECT * FROM serie ORDER BY id DESC LIMIT 1;";

        // Connection a la db et creation du statement et du resultat
        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm);
        ) {

            // Changer les valeurs des ?
            commandeStatement.setInt(1, ser.getSeanceId());
            commandeStatement.setInt(2, ser.getExerciceId());
            commandeStatement.setInt(3, ser.getOrdre());
            commandeStatement.setInt(4, ser.getReps());
            commandeStatement.setDouble(5, ser.getChargeKg());

            // Executer la commande
            commandeStatement.executeUpdate();
            System.out.println("\nSerie ajoutee avec succes !");

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm2);
            ResultSet rs = commandeStatement.executeQuery();) {
            //Donner l'id a l'objet.
            ser = lireSerie(rs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ser;
    }

    public List<Serie> trouverParSeance(int id){
        String comm = "SELECT * FROM serie WHERE seance_id = ?";
        List<Serie> ser = new ArrayList<>();

        try (Connection db = Database.getConnection();
             PreparedStatement commandeStatement = db.prepareStatement(comm)){

            commandeStatement.setInt(1, id);
            try(ResultSet rs = commandeStatement.executeQuery()){
                while (rs.next()){
                    ser.add(lireSerie(rs));
                }
                return ser;
            } catch (SQLException e) {
                System.out.println("Aucune serie trouve");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Serie> trouverParExercice(int id){
        String comm = "SELECT * FROM serie WHERE exercice_id = ?";
        List<Serie> ser = new ArrayList<>();

        try (Connection db = Database.getConnection();
             PreparedStatement commandeStatement = db.prepareStatement(comm)){

            commandeStatement.setInt(1, id);
            try(ResultSet rs = commandeStatement.executeQuery()){
                while (rs.next()){
                    ser.add(lireSerie(rs));
                }
                return ser;
            } catch (SQLException e) {
                System.out.println("Aucune serie trouve");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public int prochainOrdre(int seance_id){
        String comm = "SELECT MAX(ordre) FROM serie WHERE seance_id = ?;";
        int index = -1;

        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm);){

            commandeStatement.setInt(1, seance_id);
            try (ResultSet rs = commandeStatement.executeQuery()){
                if (rs.next()){
                    index = rs.getInt("MAX(ordre)");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (index <= 0){
            return 1;
        } else {
            index++;
            return index;
        }
    }

    public int compterParSeance(int seance_id){
        String comm = "SELECT COUNT(seance_id) FROM serie WHERE seance_id = ?;";

        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm);){

            commandeStatement.setInt(1, seance_id);
            try (ResultSet rs = commandeStatement.executeQuery()){
                if (rs.next()){
                    return rs.getInt("COUNT(seance_id)");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
