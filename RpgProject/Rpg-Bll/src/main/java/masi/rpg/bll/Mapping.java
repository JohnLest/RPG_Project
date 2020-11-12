package masi.rpg.bll;

import masi.rpg.model.databaseModel.*;

public class Mapping {
    public static Classe toClasse(Object obj){
        return (Classe) obj;
    }
    public static Combatant toCombatant(Object obj){
        return (Combatant) obj;
    }
    
}
