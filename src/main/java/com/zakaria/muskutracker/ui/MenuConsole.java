package com.zakaria.muskutracker.ui;

import com.zakaria.muskutracker.model.Exercice;
import com.zakaria.muskutracker.model.Seance;
import com.zakaria.muskutracker.model.Serie;
import com.zakaria.muskutracker.service.EntrainementService;
import com.zakaria.muskutracker.service.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    private Scanner scanner = new Scanner(System.in);

    private EntrainementService es;
    private Seance nouvelleSeance;

    public MenuConsole(EntrainementService es){
        this.es = es;
    }

    static private int lireEntier(Scanner scanner ,String message, int min, int max){
        int choix = 0;
        boolean valide = false;
        System.out.print(message);
        while (!valide) {
            String saisie = scanner.nextLine();
            try {
                choix = Integer.parseInt(saisie);
                if (choix >= min && choix <= max) {
                    valide = true;
                } else {
                    System.out.print("Le nombre doit être entre " + min + " et " + max + ". Réécris : ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide. Réécris : ");
            }
        }
        return choix;
    }

    public void lancer() {
        int choix = 0;
        do {
            try{
                System.out.println(
                        "1. Démarrer une séance\n" +
                                "2. Ajouter une série à la séance en cours\n" +
                                "3. Terminer la séance en cours\n" +
                                "4. Voir l'historique\n" +
                                "5. Voir le détail d'une séance\n" +
                                "6. Supprimer une séance\n" +
                                "7. Quitter"
                );
                choix = lireEntier(scanner, "Choisir une option : ", 1, 7);
                if (choix == 1) {
                    demarrerSeance();
                } else if (choix == 2){
                    ajouterSerieSeanceEnCours();
                } else if (choix == 3){
                    terminerSeance();
                } else if (choix == 4){
                    voirHistorique();
                } else if (choix == 5){
                    voirDetailSeance();
                } else if (choix == 6){
                    supprimerUneSeance();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (choix != 7);
        System.out.println("Au revoir !");
    }

    private void demarrerSeance(){
        System.out.print("Ajouter une note a la seance : ");
        String note = scanner.nextLine();
        nouvelleSeance = es.demarrerSeance(LocalDate.now(), note);
    }

    private void ajouterSerieSeanceEnCours(){
        if (nouvelleSeance != null){
            int choix;
            int reps;
            double charge;


            for (Exercice e : es.exercicesDisponibles()){
                System.out.println(e);
            }
            choix = lireEntier(scanner, "Veuillez choisir un exercice [id] : ", 0, 1000);

            reps = lireEntier(scanner, "Ajouter combien de repetitions : ", 1, 100);

            charge = lireEntier(scanner, "Ajouter la charge : ", 0, 100);

            es.ajouterSerie(nouvelleSeance.getId(), choix, reps, charge);
        } else {
            System.out.println("Aucune seance en cours");
        }
    }

    private void terminerSeance(){
        nouvelleSeance = null;
    }

    private void voirHistorique(){
        int index = 1;
        for (Seance s : es.historique()){
            System.out.println("[" + index + "]" + s);
            index++;
        }
    }

    private void voirDetailSeance(){

        List<Seance> lesSeances = es.historique();

        int choix = 0;
        voirHistorique();
        if (lesSeances.size() > 0){
            if (lesSeances.size() > 1){
                choix = lireEntier(scanner, "Choisir la seance a voir : ", 1, lesSeances.size());
                for (Serie s : es.detailSeance(lesSeances.get(choix-1).getId())){
                    System.out.println(s);
                }
            } else {
                System.out.println(es.detailSeance(lesSeances.get(0).getId()));
            }
        }


    }

    private void supprimerUneSeance(){
        List<Seance> lesSeances = es.historique();
        voirHistorique();
        int choix = 0;

        if (lesSeances.isEmpty()){
            System.out.println("Aucune seance !");
        } else {
            if (lesSeances.size() != 1){
                choix = lireEntier(scanner, "Choisir la seance a supprimer : ", 1, lesSeances.size());
                es.supprimerSeance(lesSeances.get(choix-1).getId());
                if (nouvelleSeance != null && lesSeances.get(choix-1).getId() == nouvelleSeance.getId()){
                    nouvelleSeance = null;
                }
            } else {
                choix = lireEntier(scanner, "Voulez vous supprimer votre seance actuelle ? [0] Non, [1] Oui", 0, 1);
                if (choix == 1){
                    es.supprimerSeance(lesSeances.get(0).getId());
                    if (lesSeances.get(0) != null){
                        nouvelleSeance = null;
                    }
                }
            }
        }
    }

}
