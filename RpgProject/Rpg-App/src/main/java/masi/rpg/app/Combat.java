package masi.rpg.app;

import java.util.List;
import java.util.Random;
import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;

public class Combat {
    private IPersoService persoService;
    private IStatService statService;
    private Equipe equipeEnnemie;
    private Equipe equipeAllie;
    private List<DetailCombattant> lstEnnemie;
    private DetailCombattant combattant;
    private DetailCombattant adversaire;
    private int atk;
    private int def;
    private Boolean agro;

    public Combat(
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
        Combat();
    }

    private void Combat(){
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

    private void Attaque() {
        if(!isAlive(adversaire)) return;
        int degat = Degat();
        if(degat <= 0){
            System.out.println(String.format("%s n'inflige pas de degats", combattant.getCombattant().getPrenom()));
            return;
        }
        System.out.println(String.format("%s inflige %d de degats à %s", combattant.getCombattant().getPrenom(), degat, adversaire.getCombattant().getPrenom()));
        persoService.UpdatePVValue(adversaire, degat );
        statService.addDegat(equipeEnnemie.getStatEquipe(), degat);
        if(!isAlive(adversaire)){
            persoService.UpdatePVValue(adversaire, -1);
            lstEnnemie.remove(adversaire);
            combattant.clearCaseContact(adversaire);
            agro = false;
            System.out.println(String.format("%s est mort", adversaire.getCombattant().getPrenom()));
        }
    }

    private int Degat(){
        statService.addAtk(equipeAllie.getStatEquipe(), combattant.getCombattant().getAtkVal());
        System.out.println(
                    String.format(
                        "%s (%d) attaque %s (%d)", 
                        combattant.getCombattant().getPrenom(), combattant.getCombattant().getPVVal(), 
                        adversaire.getCombattant().getPrenom(), adversaire.getCombattant().getPVVal()
                        )
                    );
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
