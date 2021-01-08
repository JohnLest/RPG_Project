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
        List<Thread> tList = new ArrayList<>();

        for (DetailCombattant combattant : equipeA) {
            Runnable r = (() -> {
                System.out.println("Combat A");
                new Combat(equipeB, combattant, persoService);
            });
            Thread t = new Thread(r);
            tList.add(t);
            t.start();
        }

        for (DetailCombattant combattant : equipeB) {
            Runnable r = (() -> {
                System.out.println("Combat B");
                new Combat(equipeA, combattant, persoService);
            });
            Thread t = new Thread(r);
            tList.add(t);
            t.start();
        }
   
        for (Thread t : tList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        for (Combattant combattant : view) {
            System.out.println(String.format("%s fini avec %d points de vies", combattant.getPrenom(), combattant.getPVVal()));
        }

        for (Combattant combattant : view) {
            persoService.UpdateView(combattant, combattant.getID());
        }


        System.out.println("Stop1");
        System.out.println("Stop2");
        // ------------------------

    }
}
