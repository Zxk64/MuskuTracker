package com.zakaria.muskutracker;
import com.zakaria.muskutracker.dao.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Database.initialiser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}