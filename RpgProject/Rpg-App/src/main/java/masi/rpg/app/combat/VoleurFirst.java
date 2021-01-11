package masi.rpg.app.combat;

import java.util.Random;
import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;

public class VoleurFirst extends GenericCombat {

    private String classe;

    public VoleurFirst(
        Equipe equipeEnnemie, 
        Equipe equipeAllie, 
        DetailCombattant combattant,
        IPersoService persoService, 
        IStatService statService
    ) {
        super(equipeEnnemie, equipeAllie, combattant, persoService, statService);
        classe = "Voleur";
        Combat();
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
            if(
                ((checkRestClasse(classe) && adversaire.getCombattant().getClasse().equals(classe)) || 
                !checkRestClasse(classe)) && 
                adversaire.getCaseContact().size() < 4) {
                System.out.println(
                    String.format("%s prend %s pour cible", combattant.getCombattant().getPrenom(), adversaire.getCombattant().getPrenom()));
                def = adversaire.getCombattant().getDefVal();
                adversaire.fillCaseContact(combattant);
                agro = true;
            }
        }
    }
}
