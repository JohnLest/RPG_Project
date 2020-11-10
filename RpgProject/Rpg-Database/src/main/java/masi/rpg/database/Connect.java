package masi.rpg.database;

import java.sql.*;
import johnlest.tools.*;

public class Connect {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
        String connectionString = ConfigFile.config("rpg.connectionString").toString();
        String user = ConfigFile.config("rpg.user", ".secret").toString();
        String password = ConfigFile.config("rpg.password", ".secret").toString();
        try {
            return DriverManager.getConnection(connectionString, user, password);

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
