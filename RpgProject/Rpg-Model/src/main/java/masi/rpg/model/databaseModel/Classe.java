package masi.rpg.model.databaseModel;

import java.io.Serializable;

public class Classe implements Serializable {
    private int ID_Classe;
    private String Nom_Classe;
    private int AtkMin;
    private int AtkMax; 
    private int DefMin;
    private int DefMax;
    private int PVMin;
    private int PVMax;
    private int CritMin;
    private int CritMax;
    private int ParadeMin;
    private int ParadeMax;
    private int InitMin;
    private int InitMax;

    public int getID_Classe() {
        return this.ID_Classe;
    }
    public void setID_Classe(int ID_Classe) {
        this.ID_Classe = ID_Classe;
    }

    public String getNom_Classe() {
        return this.Nom_Classe;
    }
    public void setNom_Classe(String Nom_Classe) {
        this.Nom_Classe = Nom_Classe;
    } 

    public int getAtkMin(){
        return this.AtkMin;
    }
    public void setAtkMin(int AtkMin){
        this.AtkMin = AtkMin;
    }

    public int getAtkMax(){
        return this.AtkMax;
    }
    public void setAtkMax(int AtkMax){
        this.AtkMax = AtkMax;
    }

    public int getDefMin(){
        return this.DefMin;
    }
    public void setDefMin(int DefMin){
        this.DefMin = DefMin;
    }

    public int getDefMax(){
        return this.DefMax;
    }
    public void setDefMax(int DefMax){
        this.DefMax = DefMax;
    }

    public int getPVMin(){
        return this.PVMin;
    }
    public void setPVMin(int PVMin){
        this.PVMin = PVMin;
    }

    public int getPVMax(){
        return this.PVMax;
    }
    public void setPVMax(int PVMax){
        this.PVMax = PVMax;
    }

    public int getCritMin(){
        return this.CritMin;
    }
    public void setCritMin(int CritMin){
        this.CritMin = CritMin;
    }

    public int getCritMax(){
        return this.CritMax;
    }
    public void setCritMax(int CritMax){
        this.CritMax = CritMax;
    }

    public int getParadeMin(){
        return this.ParadeMin;
    }
    public void setParadeMin(int ParadeMin){
        this.ParadeMin = ParadeMin;
    }

    public int getParadeMax(){
        return this.ParadeMax;
    }
    public void setParadeMax(int ParadeMax){
        this.ParadeMax = ParadeMax;
    }

    public int getInitMin(){
        return this.InitMin;
    }
    public void setInitMin(int InitMin){
        this.InitMin = InitMin;
    }

    public int getInitMax(){
        return this.InitMax;
    }
    public void setInitMax(int InitMax){
        this.InitMax = InitMax;
    }
}
