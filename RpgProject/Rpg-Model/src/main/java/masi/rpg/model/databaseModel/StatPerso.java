package masi.rpg.model.databaseModel;

import java.io.Serializable;

public class StatPerso implements Serializable {
    private int id;
    private int idPerso;
    private int pvPerdu;
    private int pvGagne;
    private short isFirstCombat;
    private int idStatEquipe;

    public int getID_StatPerso(){
        return this.id;
    }
    public void setID_StatPerso(int val){
        this.id = val;
    }

    public int getID_Perso(){
        return this.idPerso;
    }
    public void setID_Perso(int val){
        this.idPerso = val;
    }

    public int getPVPerdu(){
        return this.pvPerdu;
    }
    public void setPVPerdu(int val){
        this.pvPerdu = val;
    }

    public int getPVGagner(){
        return this.pvGagne;
    }
    public void setPVGagner(int val){
        this.pvGagne = val;
    }

    public short getIsFirstcombat(){
        return this.isFirstCombat;
    }
    public void setIsFirstCombat(Short val){
        this.isFirstCombat = val;
    }

    public int getID_Stat_Equipe(){
        return this.idStatEquipe;
    }
    public void setID_Stat_Equipe(int val){
        this.idStatEquipe = val;
    }
    
}
