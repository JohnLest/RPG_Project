package masi.rpg.app;

import java.sql.Connection;
import java.util.List;

import masi.rpg.bll.services.*;
import masi.rpg.bll.services.interfaces.*;
import masi.rpg.model.databaseModel.Combatant;

public class Controler {

    private IPersoService persoService;

    public Controler(Connection connect) {
        this.persoService = new PersoService(connect);
        Controler();
    }

    private void Controler() {
        //persoService.CreateNewPerso();
        List<Combatant> view =  persoService.GetConbatantView();
        for (Combatant combatant : view) {
            System.out.println(combatant.getPrenom() + combatant.getNom() + combatant.getClasse());
        }
    }
}
