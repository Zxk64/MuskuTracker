package com.zakaria.muskutracker.dao;

import com.zakaria.muskutracker.model.Exercice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciceDao {

    private Exercice lireExercice(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int wgerId = rs.getInt("wger_id");
        if (rs.wasNull()){
            wgerId = -1;
        }
        String nom = rs.getString("nom");
        String categorie = rs.getString("categorie");
        String muscles = rs.getString("muscles");

        Exercice nouvelExo = new Exercice(wgerId, nom, categorie, muscles);
        nouvelExo.setId(id);

        return nouvelExo;
    }

    public Exercice ajouter(Exercice exo) {
        try{
            // Connexion a la db
            Connection db = Database.getConnection();
            // Preparation de la commande
            String comm = "INSERT INTO exercice (wger_id, nom, categorie, muscles) VALUES (?, ?, ?, ?);";
            PreparedStatement commandeStatement = db.prepareStatement(comm);

            // Changer les valeurs des ?
            commandeStatement.setInt(1, exo.getWgerId());
            commandeStatement.setString(2, exo.getNom());
            commandeStatement.setString(3, exo.getCategorie());
            commandeStatement.setString(4, exo.getMuscles());

            // Executer la commande
            commandeStatement.executeUpdate();
            System.out.println("\nExercice ajoutee avec succes !");

            //Donner l'id a l'objet.
            String comm2 = "SELECT * FROM exercice WHERE wger_id = " + exo.getWgerId() + ";";
            commandeStatement = db.prepareStatement(comm2);
            ResultSet rs = commandeStatement.executeQuery();
            exo = lireExercice(rs);
            db.close();
        } catch (Exception e) {
            System.out.println("Erreur d'ajout");
        }
        return exo;
    }

    public List<Exercice> trouverTous(){

        String comm = "SELECT * FROM exercice ORDER BY nom;";
        List<Exercice> exos = new ArrayList<>();

        // Connexion a la base de donnees (dans le try)
        // Preparation de la commande (dans le try)
        try(
            Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm);
            ResultSet rs = commandeStatement.executeQuery();
            ){

            while (rs.next()){
                Exercice nouvelExo = lireExercice(rs);
                exos.add(nouvelExo);
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture");
        }
        return exos;
    }

    public Exercice trouverParId(int id){

        // Commande en string et initialisation de l'objet exo a retourner
        String comm = "SELECT * FROM exercice WHERE id = ?;";
        Exercice exo;

        try( Connection db = Database.getConnection();
             PreparedStatement commandeStatement = db.prepareStatement(comm);
             ){
            commandeStatement.setInt(1, id);

            try(ResultSet rs = commandeStatement.executeQuery()){
                if (rs.next()){
                    exo = lireExercice(rs);
                    return exo;
                }
            } catch (SQLException e) {
                System.out.println("Aucun exercice trouve");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Exercice> chercherParNom(String txt){

        String comm = "SELECT * FROM exercice WHERE nom LIKE ? ;";
        List<Exercice> exos = new ArrayList<>();

        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm);
            ){

            commandeStatement.setString(1, "%" + txt + "%");


            try(ResultSet rs = commandeStatement.executeQuery()){
                while (rs.next()){
                    Exercice nouvelExo = lireExercice(rs);
                    exos.add(nouvelExo);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return exos;
    }

    public int compter(){

        String comm = "SELECT COUNT(*) FROM exercice;";
        int resultat;

        try(Connection db = Database.getConnection();
            PreparedStatement commandeStatement = db.prepareStatement(comm)){

            try(ResultSet rs = commandeStatement.executeQuery()){
                if (rs.next()){
                    resultat = rs.getInt("COUNT(*)");
                    return resultat;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return -1;
    }
}
