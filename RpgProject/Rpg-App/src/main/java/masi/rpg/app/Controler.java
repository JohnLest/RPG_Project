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
        for (int i = 0; i < 20; i++) {
            persoService.CreateNewPerso();
        }
        List<Combattant> view =  persoService.GetConbatantView();
        int size = view.size();
        List<Combattant> equipeA = new ArrayList<>(view.subList(0, size/2));
        List<Combattant> equipeB = new ArrayList<>(view.subList(size/2, size));

        // --- Temps de combat ----
        
        for (Combattant combattant : equipeB) {
            combattant.setPVVal(0);
            combattant.setNbrCombat(combattant.getNbrCombat() + 1 );
            persoService.UpdateView(combattant, combattant.getID());
        }

        // ------------------------



        System.out.println(equipeA);
        System.out.println(equipeB);

    }
}
