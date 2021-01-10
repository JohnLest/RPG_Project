package masi.rpg.app;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import masi.rpg.bll.services.*;
import masi.rpg.bll.services.interfaces.*;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.StatCombat;

public class Controler {

    private IPersoService persoService;
    private IStatService statService;

    public Controler(Connection connect) {
        this.persoService = new PersoService(connect);
        this.statService = new StatService(connect);
        Controler();
    }

    private void Controler() {
        for (int i = 0; i < 20; i++) {
            persoService.CreateNewPerso();
        }
        List<Combattant> view = persoService.GetCombattantView();
        int size = view.size();

        Equipe equipeA = persoService.SetEquipe(view.subList(0, size / 2));
        equipeA.getStatEquipe().setID_Strat(1);
        Equipe equipeB = persoService.SetEquipe(view.subList(size / 2, size));
        equipeB.getStatEquipe().setID_Strat(1);

        statService.InsertStatEquipe(equipeA.getStatEquipe());
        statService.InsertStatEquipe(equipeB.getStatEquipe());

        // --- Temps de combat ----
        List<Thread> tList = new ArrayList<>();

        for (DetailCombattant combattant : equipeA.getDetailCombattant()) {
            Runnable r = (() -> {
                statService.setId(
                    combattant.getStatPerso(), 
                    equipeA.getStatEquipe(), 
                    combattant.getCombattant()
                    );
                new Combat(equipeB, equipeA, combattant, persoService, statService);
            });
            Thread t = new Thread(r);
            tList.add(t);
            t.start();
        }

        for (DetailCombattant combattant : equipeB.getDetailCombattant()) {
            Runnable r = (() -> {
                statService.setId(
                    combattant.getStatPerso(), 
                    equipeB.getStatEquipe(), 
                    combattant.getCombattant()
                    );
                new Combat(equipeA, equipeB, combattant, persoService, statService);
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
            persoService.UpdateView(combattant, combattant.getID());
        }
        statService.updateStatEquipe(equipeA.getStatEquipe(), equipeA.getDetailCombattant().size());
        statService.updateStatEquipe(equipeB.getStatEquipe(), equipeB.getDetailCombattant().size());

        StatCombat sc = new StatCombat();
        if(equipeA.getDetailCombattant().size() > 0 ){
            statService.setWinner(sc, equipeA.getStatEquipe().getID_StatEquipe());
            statService.setLooser(sc, equipeB.getStatEquipe().getID_StatEquipe());
        }
        else if(equipeB.getDetailCombattant().size() > 0 ){
            statService.setWinner(sc, equipeB.getStatEquipe().getID_StatEquipe());
            statService.setLooser(sc, equipeA.getStatEquipe().getID_StatEquipe());
        }
        statService.InsertStatCombat(sc);

        // ------------------------

    }
}
