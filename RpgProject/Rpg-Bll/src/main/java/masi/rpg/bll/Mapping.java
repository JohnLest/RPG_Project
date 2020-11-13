package masi.rpg.bll;

import masi.rpg.model.databaseModel.*;

public class Mapping {
    public static Classe toClasse(Object obj){
        return (Classe) obj;
    }
    public static Combatant toCombatant(Object obj){
        return (Combatant) obj;
    }

    public static Perso toPerso(Object obj){
        return (Perso) obj;
    }
    public static Perso combatantToPerso(Combatant c){
        Perso p = new Perso();
        p.setID_Perso(c.getID());
        p.setPVVal(c.getPVVal());
        p.setNbrCombat(c.getNbrCombat());
        
        return p;
    }
}
