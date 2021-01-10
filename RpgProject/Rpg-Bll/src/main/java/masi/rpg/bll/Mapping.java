package masi.rpg.bll;

import masi.rpg.model.DetailCombattant;
import masi.rpg.model.databaseModel.*;

public class Mapping {
    public static Classe toClasse(Object obj){
        return (Classe) obj;
    }
    public static Combattant toCombattant(Object obj){
        return (Combattant) obj;
    }

    public static Perso toPerso(Object obj){
        return (Perso) obj;
    }

    public static Strat toStrat(Object obj){
        return (Strat) obj;
    }


    public static Perso combattantToPerso(Combattant c){
        Perso p = new Perso();
        p.setID_Perso(c.getID());
        p.setPVVal(c.getPVVal());
        p.setNbrCombat(c.getNbrCombat());
        
        return p;
    }

    /*
    public static StatPerso dcombattantToStatPerso(DetailCombattant dc){
        StatPerso sp = new StatPerso();
        sp.setID_Perso(dc.getCombattant().getID());
        sp.setPVPerdu(dc.getPVPerdu());
        sp.setPVGagner(dc.getPVGagner());
        sp.setIsFirstCombat(dc.getIsFirstcombat());
        sp.setID_Stat_Equipe(1);

        return sp;
    }*/
}
