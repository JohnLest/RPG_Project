package masi.rpg.app;

import java.util.List;
import java.util.Random;
import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.model.DetailCombattant;

public class Combat {
    private IPersoService persoService;
    private List<DetailCombattant> EquipeEnnemie;
    private DetailCombattant combattant;
    private DetailCombattant adversaire;
    private Boolean agro;

    public Combat(List<DetailCombattant> EquipeEnnemie, DetailCombattant combattant, IPersoService persoService) {
        this.persoService = persoService;
        this.combattant = combattant;
        this.EquipeEnnemie = EquipeEnnemie;
        this.agro = false;

        while (!EquipeEnnemie.isEmpty()) {
            Init();
            if (isAlive(combattant)) Agro();
            if (agro && isAlive(combattant)) Attaque();
            if (!isAlive(combattant)) break;
        }
    }

    private void Init() {
        try {
            Thread.sleep(1000 / combattant.getCombattant().getInitVal());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void Agro() {
        if(agro) return;
        else if (!combattant.getCaseContact().isEmpty()){
            System.out.println(String.format("%s est deja engagé", combattant.getCombattant().getPrenom()));
            adversaire = combattant.getCaseContact().get(0);
            agro = true;
            return; 
        }
        Random r = new Random();
        while (!agro && !EquipeEnnemie.isEmpty()) {
            adversaire = EquipeEnnemie.get(r.nextInt(EquipeEnnemie.size()));
            if (adversaire.getCaseContact().size() < 4) {
                System.out.println(
                    String.format("%s prend %s pour cible", combattant.getCombattant().getPrenom(), adversaire.getCombattant().getPrenom()));
                adversaire.fillCaseContact(combattant);
                agro = true;
            }
        }
    }

    private void Attaque() {
        if(!isAlive(adversaire)) return;
        int degat = Degat();
        if(degat <= 0){
            System.out.println(String.format("%s n'inflige pas de degats", combattant.getCombattant().getPrenom()));
            return;
        }
        System.out.println(String.format("%s inflige %d de degats à %s", combattant.getCombattant().getPrenom(), degat, adversaire.getCombattant().getPrenom()));
        persoService.UpdatePVValue(adversaire.getCombattant(), degat );
        if(!isAlive(adversaire)){
            persoService.UpdatePVValue(adversaire.getCombattant(), -1);
            EquipeEnnemie.remove(adversaire);
            combattant.clearCaseContact(adversaire);
            agro = false;
            System.out.println(String.format("%s est mort", adversaire.getCombattant().getPrenom()));
        }
    }

    private int Degat(){
        System.out.println(
                    String.format("%s attaque %s", combattant.getCombattant().getPrenom(), adversaire.getCombattant().getPrenom()));
        Random r = new Random();
        int esquive = r.nextInt(101);
        if(esquive <= adversaire.getCombattant().getParadeVal()){
            System.out.println(String.format("%s esquive l'attaque", adversaire.getCombattant().getPrenom()));
            return 0;
        }

        int crit = r.nextInt(101);
        if (crit <= combattant.getCombattant().getCritVal())
        {
            System.out.println(String.format("%s fait un coup critique", combattant.getCombattant().getPrenom()));
            return combattant.getCombattant().getAtkVal();
        }
        return combattant.getCombattant().getAtkVal() - adversaire.getCombattant().getDefVal();
    }

    private boolean isAlive(DetailCombattant dc){
        if(dc.getCombattant().getPVVal() > 0)
            return true;
        return false;
    }
}
