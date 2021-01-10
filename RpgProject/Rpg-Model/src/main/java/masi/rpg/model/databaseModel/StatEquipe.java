package masi.rpg.model.databaseModel;

import java.io.Serializable;

public class StatEquipe implements Serializable {
    private int id;
    private int totAtk;
    private int totdeg;
    private int totPV;
    private int totSoins;
    private int idStrat;
    private short nbrGuerriers;
    private short nbrMages;
    private short nbrVoleurs;
    private short nbrPretres;
    private short nbrSurivants;

    public int getID_StatEquipe(){
        return this.id;
    }
    public void setID_StatEquipe(int val){
        this.id = val;
    }

    public int getTotalAtk(){
        return totAtk;
    }
    public void setTotalAtk(int val){
        this.totAtk = val;
    }

    public int getTotalDegats(){
        return totdeg;
    }
    public void setTotalDegats(int val){
        this.totdeg = val;
    }

    public int getTotalPV(){
        return totPV;
    }
    public void setTotalPV(int val){
        this.totPV = val;
    }

    public int getTotalSoins(){
        return totSoins;
    }
    public void setTotalSoins(int val){
        this.totSoins = val;
    }

    public int getID_Strat(){
        return this.idStrat;
    }
    public void setID_Strat(int val){
        this.idStrat = val;
    }

    public short getNbrGuerriers(){
        return nbrGuerriers;
    }
    public void setNbrGuerriers(short val){
        this.nbrGuerriers = val;
    }
    public void incremanteNbrGuerriers(){
        this.nbrGuerriers = (short)(this.nbrGuerriers + 1);
    }

    public short getNbrMage(){
        return nbrMages;
    }
    public void setNbrMage(short val){
        this.nbrMages = val;
    }
    public void incremanteNbrMage(){
        this.nbrMages = (short)(this.nbrMages + 1);
    }

    public short getNbrVoleurs(){
        return nbrVoleurs;
    }
    public void setNbrVoleurs(short val){
        this.nbrVoleurs = val;
    }
    public void incremanteNbrVoleurs(){
        this.nbrVoleurs = (short)(this.nbrVoleurs + 1);
    }

    public short getNbrPretre(){
        return nbrPretres;
    }
    public void setNbrPretre(short val){
        this.nbrPretres = val;
    }    
    public void incremanteNbrPretre(){
        this.nbrPretres = (short)(this.nbrPretres + 1);
    }

    public short getNbrSurvivant(){
        return nbrSurivants;
    }
    public void setNbrSurvivant(short val){
        this.nbrSurivants = val;
    }
}
