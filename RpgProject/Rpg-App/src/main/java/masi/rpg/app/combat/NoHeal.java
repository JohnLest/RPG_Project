package masi.rpg.app.combat;

import java.util.Random;
import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;

public class NoHeal extends GenericCombat {
    public NoHeal(Equipe equipeEnnemie, Equipe equipeAllie, DetailCombattant combattant, IPersoService persoService,
            IStatService statService) {
        super(equipeEnnemie, equipeAllie, combattant, persoService, statService);
        Combat();
    }

    protected void Combat() {
        if (persoService.IsFirstCombat(combattant))
            System.out.println(String.format("Premier combat pour %s", combattant.getCombattant().getPrenom()));
        while (!lstEnnemie.isEmpty()) {
            System.out.println("nouveau tours pour " + combattant.getCombattant().getPrenom());
            Init();
            if (isAlive(combattant))
                Agro();
            if (agro && isAlive(combattant))
                Attaque();
            if (!isAlive(combattant))
                break;
        }
        statService.InsertStatPerso(adversaire.getStatPerso());
        System.out.println("Fin du tour");
    }

    protected void Attaque() {
        if (!isAlive(adversaire))
            return;
        int degat = Degat();
        if (degat <= 0) {
            System.out.println(String.format("%s n'inflige pas de degats", combattant.getCombattant().getPrenom()));
            return;
        }
        System.out.println(String.format("%s inflige %d de degats à %s", combattant.getCombattant().getPrenom(), degat,
                adversaire.getCombattant().getPrenom()));
        persoService.UpdatePVValue(adversaire, degat);
        statService.addDegat(equipeEnnemie.getStatEquipe(), degat);
        if (!isAlive(adversaire)) {
            persoService.UpdatePVValue(adversaire, 0);
            lstEnnemie.remove(adversaire);
            combattant.clearCaseContact(adversaire);
            agro = false;
            System.out.println(String.format("%s est mort", adversaire.getCombattant().getPrenom()));
        }
    }
}
