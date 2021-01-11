package masi.rpg.app;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.BranchElement;

import masi.rpg.app.combat.*;
import masi.rpg.bll.services.*;
import masi.rpg.bll.services.interfaces.*;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.StatCombat;

public class Controler {

    private IPersoService persoService;
    private IStatService statService;
    private IStratService stratService;

    public Controler(Connection connect) {
        this.persoService = new PersoService(connect);
        this.statService = new StatService(connect);
        this.stratService = new StratService(connect);
        Controler();
    }

    private void Controler() {
        for (int i = 0; i < 20; i++) {
            persoService.CreateNewPerso();
        }
        List<Combattant> view = persoService.GetCombattantView();
        int size = view.size();

        Equipe equipeA = persoService.SetEquipe(view.subList(0, size / 2));
        statService.setStrat(equipeA.getStatEquipe(), stratService.getStrat().getID_Strat());
        Equipe equipeB = persoService.SetEquipe(view.subList(size / 2, size));
        statService.setStrat(equipeB.getStatEquipe(), stratService.getStrat().getID_Strat());

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
                SwitchStrat(equipeA, equipeB, combattant); 
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
                    SwitchStrat(equipeB, equipeA, combattant); 
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

    private void SwitchStrat(Equipe equipe, Equipe ennemi, DetailCombattant combattant){
        switch (equipe.getStatEquipe().getID_Strat()) {
            case 1:
                new Combat(ennemi, equipe, combattant, persoService, statService);
                break;
            case 2:
                new NoHeal(ennemi, equipe, combattant, persoService, statService);
                break;
            case 3:
                new GuerrierFirst(ennemi, equipe, combattant, persoService, statService);
                break;
            case 4:
                new MageFirst(ennemi, equipe, combattant, persoService, statService);
                break;
            case 5:
                new PretreFirst(ennemi, equipe, combattant, persoService, statService);
                break;
            case 6:
                new VoleurFirst(ennemi, equipe, combattant, persoService, statService);
                break;
        }
    }
}

