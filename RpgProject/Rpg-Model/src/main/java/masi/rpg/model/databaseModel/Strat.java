package masi.rpg.model.databaseModel;

import java.io.Serializable;

public class Strat implements Serializable {
    private int id;
    private String nomStrat;
    private String commentaire;

    public int getID_Strat(){
        return this.id;
    }
    public void setID_Strat(int val){
        this.id = val;
    }

    public String getNom_Strat(){
        return this.nomStrat;
    }
    public void setNom_Strat(String val){
        this.nomStrat = val;
    }

    public String getCommentaire_Strat(){
        return this.commentaire;
    }
    public void setCommentaire_Strat(String val){
        this.commentaire = val;
    }
}
