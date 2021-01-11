package masi.rpg.app.combat;

import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;

public class Combat extends GenericCombat {
    public Combat(
        Equipe equipeEnnemie, 
        Equipe equipeAllie, 
        DetailCombattant combattant,
        IPersoService persoService, 
        IStatService statService
    ){
        super(equipeEnnemie, equipeAllie, combattant, persoService, statService);
        Combat();
    }
}