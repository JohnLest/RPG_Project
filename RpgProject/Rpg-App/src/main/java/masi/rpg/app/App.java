package masi.rpg.app;

import java.sql.Connection;
import masi.rpg.database.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World! Main");
        Connection connect = Connect.connect();
        new Controler(connect);
        Disconnect.disconnect(connect);
    }
}
