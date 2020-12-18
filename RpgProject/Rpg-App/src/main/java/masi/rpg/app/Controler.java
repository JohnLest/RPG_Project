package masi.rpg.app;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import masi.rpg.bll.services.*;
import masi.rpg.bll.services.interfaces.*;
import masi.rpg.model.DetailCombattant;
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
        int size = view.size();
        List<DetailCombattant> equipeA = new ArrayList<>(persoService.SetEquipe(view.subList(0, size / 2), 'A'));
        List<DetailCombattant> equipeB = new ArrayList<>(persoService.SetEquipe(view.subList(size / 2, size), 'B'));

        // --- Temps de combat ----

        for (DetailCombattant combattant : equipeA) {
            Runnable r = (() -> {
                new Combat(equipeB, combattant);
            });
            Thread t = new Thread(r);
            t.start();
        }

        for (DetailCombattant combattant : equipeB) {
            Runnable r = (() -> {
                new Combat(equipeA, combattant);
            });
            Thread t = new Thread(r);
            t.start();
        }

        System.out.println("Stop");
        // ------------------------

    }
}
