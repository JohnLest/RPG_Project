package masi.rpg.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFile {
    public static Object config(String key) {
        Properties prop = new Properties();
        String fileName = "resources/app.conf";
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        try {
            prop.load(is);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return prop.getProperty(key); 
    }
}
