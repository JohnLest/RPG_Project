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
        List<Combattant> view =  persoService.GetCombattantView();
        int size = view.size();
        persoService.SetEquipe(view.subList(0, size/2), 'A');
        persoService.SetEquipe(view.subList(size/2, size), 'B');

        // --- Temps de combat ----
        

        // ------------------------

    }
}
