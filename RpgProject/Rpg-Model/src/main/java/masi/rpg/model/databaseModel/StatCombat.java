package masi.rpg.model.databaseModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatCombat implements Serializable{
    private int id;
    private String date;
    private int winner;
    private int looser;
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss.SSS");

    public StatCombat() {
        this.date = format.format(new Date());
    }
    
    public int getID_StatCombat(){
        return this.id;
    }
    public void setID_StatCombat(int val){
        this.id = val;
    }

    public String getDateCombat (){
        return this.date;
    }
    public void setDateCombat(Date val){
        this.date = format.format(val);
    }

    public int getID_EquipeVainqueur(){
        return this.winner;
    }
    public void setID_EquipeVainqueur(int val){
        this.winner = val;
    }

    public int getID_EquipeVaincu(){
        return this.looser;
    }
    public void setID_EquipeVaincu(int val){
        this.looser = val;
    }
}
