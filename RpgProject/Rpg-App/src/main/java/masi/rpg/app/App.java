package masi.rpg.app;

import java.sql.Connection;
import masi.rpg.database.*;

public class App {
    public static void main(String[] args) {
        Connection connect = Connect.connect();
        new Controler(connect);
        Disconnect.disconnect(connect);
    }
}
