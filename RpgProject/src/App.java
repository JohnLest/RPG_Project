import utils.ConfigFile;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println(ConfigFile.config("name"));
        System.out.println(ConfigFile.config("version"));
    }
}
