package masi.rpg.model;

import java.io.Serializable;
import java.util.List;
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.StatPerso;

public class DetailCombattant implements Serializable{
    
    private Combattant combattant;
    private List<DetailCombattant> caseContact;
    private StatPerso statPerso;

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

    public StatPerso getStatPerso(){
        return this.statPerso;
    }
    public void setStatPerso(StatPerso sp){
        this.statPerso = sp;
    }
}
