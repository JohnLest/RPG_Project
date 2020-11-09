package masi.rpg.database;

import java.sql.*;
import masi.rpg.utils.*;

public class ConnectDB 
{
    public static void connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        String connectionString = ConfigFile.config("rpg.connectionString").toString();
        String user = SecretFile.config("rpg.user").toString();
        String password = SecretFile.config("rpg.password").toString();
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(connectionString, user, password);
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery("SELECT *  FROM `rpg.classe`;");
            while (resultat.next()) {
                int id = resultat.getInt("ID_Classe");
                String classe = resultat.getString("Nom_Classe");
                System.out.println(classe);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (connexion != null)
                try {
                    connexion.close();
                } catch (SQLException ignore) {
                    System.out.println(ignore);
                }
        }
        
    }
}