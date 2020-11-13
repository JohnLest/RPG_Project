package masi.rpg.bll;

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
    public static Perso combattantToPerso(Combattant c){
        Perso p = new Perso();
        p.setID_Perso(c.getID());
        p.setPVVal(c.getPVVal());
        p.setNbrCombat(c.getNbrCombat());
        
        return p;
    }
}
