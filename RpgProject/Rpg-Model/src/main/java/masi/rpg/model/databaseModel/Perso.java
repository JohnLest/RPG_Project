package masi.rpg.model.databaseModel;

import java.io.Serializable;

public class Perso implements Serializable {
    private int id;
    private int pvVal; 
    private int nbrCombat;

    public int getID_Perso(){
        return id;
    }
    public void setID_Perso(int id){
        this.id = id;
    }

    public int getPVVal(){
        return pvVal;
    }
    public void setPVVal(int val){
        this.pvVal = val;
    }
    
    public int getNbrCombat(){
        return this.nbrCombat;
    }
    public void setNbrCombat(int val){
        this.nbrCombat = val;
    }
    
}
