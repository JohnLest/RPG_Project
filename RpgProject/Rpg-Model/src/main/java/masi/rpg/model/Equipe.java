package masi.rpg.model;

import java.io.Serializable;
import java.util.List;

import masi.rpg.model.databaseModel.StatEquipe;

public class Equipe  implements Serializable{
    private List<DetailCombattant> dc;
    private StatEquipe se;

    public List<DetailCombattant> getDetailCombattant(){
        return this.dc;
    }
    public void setDetailCombattant(List<DetailCombattant> dc){
        this.dc = dc;
    }

    public StatEquipe getStatEquipe(){
        return se;
    }
    public void setStatEquipe(StatEquipe se){
        this.se = se;
    }
    
}
