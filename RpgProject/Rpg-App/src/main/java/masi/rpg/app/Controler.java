package masi.rpg.app;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import masi.rpg.bll.services.*;
import masi.rpg.bll.services.interfaces.*;
import masi.rpg.model.databaseModel.Combattant;

public class Controler {

    private IPersoService persoService;

    public Controler(Connection connect) {
        this.persoService = new PersoService(connect);
        Controler();
    }

    private void Controler() {
        /*
         * for (int i = 0; i < 20; i++) { persoService.CreateNewPerso(); }
         */
        List<Combattant> view = persoService.GetCombattantView();
        List<Combattant> viewTest = new ArrayList<Combattant>(view.subList(0, 2));
        int size = viewTest.size();
        persoService.SetEquipe(viewTest.subList(0, size / 2), 'A');
        persoService.SetEquipe(viewTest.subList(size / 2, size), 'B');

        // --- Temps de combat ----

        for (int i = 0; i < size / 2; i++) {
            Combattant combattant = viewTest.get(i);
            Runnable r = (() -> {
                new Combat(viewTest.subList(size / 2, size), combattant);
            });
            Thread t = new Thread(r);
            t.start();
        }

        for (int i = size / 2; i < size; i++) {
            Combattant combattant = viewTest.get(i);
            Runnable r = (() -> {
                new Combat(viewTest.subList(0, size / 2), combattant);
            });
            Thread t = new Thread(r);
            t.start();
        }

        System.out.println("Stop");
        // ------------------------

    }
}
