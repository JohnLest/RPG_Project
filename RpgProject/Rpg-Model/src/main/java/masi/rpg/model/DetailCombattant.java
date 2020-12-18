package masi.rpg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import masi.rpg.model.databaseModel.Combattant;

public class DetailCombattant implements Serializable{
    
    private Combattant combattant;
    private List<DetailCombattant> caseContact;
    private char equipe;

    public Combattant getCombattant(){
        return this.combattant;
    }
    public void setCombattant(Combattant c){
        this.combattant = c;
    }

    public List<DetailCombattant> getCaseContact() {
        return this.caseContact;
    }
    public void setCaseContact(List<DetailCombattant> lc){
        this.caseContact = lc;
    }
    public void fillCaseContact(DetailCombattant c) {
        if(this.caseContact.size() < 4) this.caseContact.add(c);
    }
    public void clearCaseContact(DetailCombattant c) {
        if(!this.caseContact.isEmpty()) this.caseContact.remove(c);
    }

    public char getEquipe() {
        return this.equipe;
    }
    public void setEquipe(char val) {
        this.equipe = val;
    }
}
