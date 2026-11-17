package com.zakaria.muskutracker.dao;


import com.zakaria.muskutracker.model.Exercice;
import com.zakaria.muskutracker.model.Seance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeanceDao {

    private Seance lireSeance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String d = rs.getString("date");
        LocalDate date = LocalDate.parse(d);
        String note = rs.getString("note");

        Seance nouvelleSeance = new Seance(date, note);
        nouvelleSeance.setId(id);

        return nouvelleSeance;
    }

    public Seance ajouter(Seance sea) {

        // Preparation de la commande
        String comm = "INSERT INTO seance (date, note) VALUES (?, ?);";
        String comm2 = "SELECT * FROM seance ORDER BY id DESC LIMIT 1;";

        // Connection a la db et creation du statement et du resultat
        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm);
            ) {

            // Changer les valeurs des ?
            commandeStatement.setObject(1, sea.getDate());
            commandeStatement.setString(2, sea.getNote());
            // Executer la commande
            commandeStatement.executeUpdate();
            System.out.println("\nSeance ajoutee avec succes !");

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm2);
            ResultSet rs = commandeStatement.executeQuery();) {
            //Donner l'id a l'objet.
            sea = lireSeance(rs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sea;
    }

    public List<Seance> trouverToutes(){
        String comm = "SELECT * FROM seance ORDER BY date DESC, id DESC;";
        List<Seance> seas = new ArrayList<>();

        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm);
            ResultSet rs = commandeStatement.executeQuery();
        ){
            while (rs.next()){
                Seance nouvelleSea = lireSeance(rs);
                seas.add(nouvelleSea);
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture");
        }

        return seas;
    }

    public Seance trouverParId(int id){

        String comm = "SELECT * FROM seance WHERE id = ?";
        Seance sea;

        try (Connection db = Database.getConnection();
              PreparedStatement commandeStatement = db.prepareStatement(comm)){

            commandeStatement.setInt(1, id);
            try(ResultSet rs = commandeStatement.executeQuery()){
                if (rs.next()){
                    sea = lireSeance(rs);
                    return sea;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean supprimer(int id){
        String comm = "DELETE FROM seance WHERE id = ?";

        try (Connection db = Database.getConnection();
             PreparedStatement commandeStatement = db.prepareStatement(comm)){

            commandeStatement.setInt(1, id);
            if (commandeStatement.executeUpdate() > 0){
                System.out.println("\nSuprimme avec succes");
                return true;
            } else {
                System.out.println("\nSeance non existante");
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
