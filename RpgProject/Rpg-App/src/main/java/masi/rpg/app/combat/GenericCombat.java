package masi.rpg.app.combat;

import java.util.List;
import java.util.Random;
import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;

public class GenericCombat {

    protected IPersoService persoService;
    protected IStatService statService;
    protected Equipe equipeEnnemie;
    protected Equipe equipeAllie;
    protected List<DetailCombattant> lstEnnemie;
    protected DetailCombattant combattant;
    protected DetailCombattant adversaire;
    protected int atk;
    protected int def;
    protected Boolean agro;

    public GenericCombat(
            Equipe equipeEnnemie, 
            Equipe equipeAllie, 
            DetailCombattant combattant,
            IPersoService persoService, 
            IStatService statService) {
        this.persoService = persoService;
        this.statService = statService;
        this.combattant = combattant;
        this.equipeEnnemie = equipeEnnemie;
        this.equipeAllie = equipeAllie;
        this.lstEnnemie = equipeEnnemie.getDetailCombattant();
        this.agro = false;
    }
    protected void Combat(){
        if(persoService.IsFirstCombat(combattant)) 
            System.out.println(String.format("Premier combat pour %s", combattant.getCombattant().getPrenom()));
        while (!lstEnnemie.isEmpty()) {
            System.out.println("nouveau tours pour " + combattant.getCombattant().getPrenom());
            Init();
            if (isAlive(combattant)) Agro();
            if (agro && isAlive(combattant)) Attaque();
            if (!isAlive(combattant)) break;
        }
        statService.InsertStatPerso(adversaire.getStatPerso());
        System.out.println("Fin du tour");
    }

    protected void Agro() {
        if(agro) return;
        else if (!combattant.getCaseContact().isEmpty()){
            System.out.println(String.format("%s est deja engagé", combattant.getCombattant().getPrenom()));
            adversaire = combattant.getCaseContact().get(0);
            def = adversaire.getCombattant().getDefVal();
            agro = true;
            return; 
        }
        Random r = new Random();
        while (!agro && !lstEnnemie.isEmpty()) {
            adversaire = lstEnnemie.get(r.nextInt(lstEnnemie.size()));
            def = adversaire.getCombattant().getDefVal();
            if (adversaire.getCaseContact().size() < 4) {
                System.out.println(
                    String.format("%s prend %s pour cible", combattant.getCombattant().getPrenom(), adversaire.getCombattant().getPrenom()));
                adversaire.fillCaseContact(combattant);
                agro = true;
            }
        }
    }

    protected void Init() {
        try {
            Thread.sleep(1000 / combattant.getCombattant().getInitVal());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
    }

    protected void Attaque() {
        if (!isAlive(adversaire))
            return;
        if (Heal())
            return;
        int degat = Degat();
        if (degat <= 0) {
            System.out.println(String.format("%s n'inflige pas de degats", combattant.getCombattant().getPrenom()));
            return;
        }
        System.out.println(String.format("%s inflige %d de degats à %s", combattant.getCombattant().getPrenom(), degat,
                adversaire.getCombattant().getPrenom()));
        persoService.UpdatePVValue(adversaire, degat);
        statService.addDegat(equipeEnnemie.getStatEquipe(), degat);
        if (!isAlive(adversaire)) {
            persoService.UpdatePVValue(adversaire, 0);
            lstEnnemie.remove(adversaire);
            combattant.clearCaseContact(adversaire);
            agro = false;
            System.out.println(String.format("%s est mort", adversaire.getCombattant().getPrenom()));
        }
    }

    protected int Degat() {
        statService.addAtk(equipeAllie.getStatEquipe(), combattant.getCombattant().getAtkVal());
        System.out.println(String.format("%s (%d) attaque %s (%d)", combattant.getCombattant().getPrenom(),
                combattant.getCombattant().getPVVal(), adversaire.getCombattant().getPrenom(),
                adversaire.getCombattant().getPVVal()));
        Random r = new Random();
        int esquive = r.nextInt(101);
        if (esquive <= adversaire.getCombattant().getParadeVal()) {
            System.out.println(String.format("%s esquive l'attaque", adversaire.getCombattant().getPrenom()));
            return 0;
        }

        int crit = r.nextInt(101);
        if (crit <= combattant.getCombattant().getCritVal()) {
            System.out.println(String.format("%s fait un coup critique", combattant.getCombattant().getPrenom()));
            return combattant.getCombattant().getAtkVal();
        }
        return combattant.getCombattant().getAtkVal() - adversaire.getCombattant().getDefVal();
    }

    protected boolean Heal(){
        if(!combattant.getCombattant().getClasse().equals("Prêtre"))
            return false;
        Random r = new Random();
        int isHeal = r.nextInt(2);
        if(!agro) isHeal = 1;
        if(isHeal == 1){
            DetailCombattant allier = combattant;
            for (DetailCombattant dc : equipeAllie.getDetailCombattant()) {
                if(dc.getCombattant().getPVVal() < allier.getCombattant().getPVVal())
                    allier = dc;
            }
            persoService.UpdatePVValue(allier, combattant.getCombattant().getDefVal()/4);
            return true;
        }
        return false;
    }
    
    protected boolean isAlive(DetailCombattant dc) {
        if (dc.getCombattant().getPVVal() > 0)
            return true;
        return false;
    }

    protected boolean checkRestClasse(String classe){
        for (DetailCombattant dc : lstEnnemie) {
            if(dc.getCombattant().getClasse().equals(classe))
                return true;
        }
        return false;
    }
}
