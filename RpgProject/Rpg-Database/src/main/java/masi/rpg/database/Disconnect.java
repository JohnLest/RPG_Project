package masi.rpg.database;

import java.sql.*;

public class Disconnect {
    public static void disconnect(Connection connect) {
        if (connect != null)
        try {
            connect.close();
        } catch (SQLException ignore) {
            System.out.println(ignore);
        }
    }
}
