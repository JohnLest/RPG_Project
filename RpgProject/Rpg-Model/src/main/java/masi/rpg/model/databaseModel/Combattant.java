package masi.rpg.model.databaseModel;

import java.io.Serializable;

public class Combattant implements Serializable{
    
    // SQL data
    private int id;
    private String prenom;
    private String nom;
    private String classe;
    private int atkVal;
    private int defVal;
    private int pvVal;
    private int critVal;
    private int paradeVal;
    private int initVal;
    private int nbrCombat;

    // Combat Data
    private int caseDispo;
    private char equipe;


    public int getID(){
        return this.id;
    }
    public void setID(int id){
        this.id = id;
    }

    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String pre){
        this.prenom = pre;
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    public String getClasse(){
        return classe;
    }
    public void setClasse(String cl){
        this.classe = cl;
    }

    public int getAtkVal(){
        return atkVal;
    }
    public void setAtkVal(int val){
        this.atkVal = val;
    }

    public int getDefVal(){
        return defVal;
    }
    public void setDefVal(int val){
        this.defVal = val;
    }

    public int getPVVal(){
        return pvVal;
    }
    public void setPVVal(int val){
        this.pvVal = val;
    }
    
    public int getCritVal(){
        return critVal;
    }
    public void setCritVal(int val){
        this.critVal = val;
    }
    
    public int getParadeVal(){
        return paradeVal;
    }
    public void setParadeVal(int val){
        this.paradeVal = val;
    }
    
    public int getInitVal(){
        return initVal;
    }
    public void setInitVal(int val){
        this.initVal = val;
    }
    
    public int getNbrCombat(){
        return this.nbrCombat;
    }
    public void setNbrCombat(int val){
        this.nbrCombat = val;
    }

    public int getCaseDispo() {
        return this.caseDispo;
    }
    public void setCaseDispo(int val){
        this.caseDispo = val;
    }
    public void clearCase() {
        if(this.caseDispo < 4) this.caseDispo ++;
    }
    public void fillCase() {
        if(this.caseDispo > 0) this.caseDispo --;
    }

    public char getEquipe() {
        return this.equipe;
    }
    public void setEquipe(char val) {
        this.equipe = val;
    }
}
