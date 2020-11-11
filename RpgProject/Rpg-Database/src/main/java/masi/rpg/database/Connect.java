package masi.rpg.database;

import java.sql.*;
import org.apache.commons.dbutils.DbUtils;
import johnlest.tools.*;

public class Connect {
    public static Connection connect() {
        String connectionString = ConfigFile.config("rpg.connectionString").toString();
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String user = ConfigFile.config("rpg.user", ".secret").toString();
        String password = ConfigFile.config("rpg.password", ".secret").toString();

        boolean connect; 
        connect = DbUtils.loadDriver(jdbcDriver);
        if (!connect)
            System.out.println("Probleme Driver");
        try {
            return DriverManager.getConnection(connectionString, user, password);
    
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
