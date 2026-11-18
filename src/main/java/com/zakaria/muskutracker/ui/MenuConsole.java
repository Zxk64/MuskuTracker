package com.zakaria.muskutracker.ui;

import com.zakaria.muskutracker.model.Seance;
import com.zakaria.muskutracker.service.EntrainementService;
import com.zakaria.muskutracker.service.ValidationException;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuConsole {

    Scanner scanner = new Scanner(System.in);
    EntrainementService es;


    Seance nouvelleSeance;

    public MenuConsole(EntrainementService es){
        this.es = es;
    }

    public void lancer(){

    }

    private int lireEntier(String message, int min, int max){
        int r = min -1;
        do {
            try {
                int result = Integer.parseInt(message);
                r = result;
                if (r < min || r > max){
                    message = scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println(" Erreur de conversion : " + e);
            }
        }while (r >= min && r <= max);
        return r;
    }
}
