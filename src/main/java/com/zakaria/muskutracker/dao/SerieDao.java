package com.zakaria.muskutracker.dao;

import com.zakaria.muskutracker.model.Exercice;
import com.zakaria.muskutracker.model.Serie;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
