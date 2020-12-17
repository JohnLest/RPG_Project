package masi.rpg.app;

import java.util.List;
import java.util.Random;

import masi.rpg.model.databaseModel.Combattant;

public class Combat {
    private List<Combattant> list;
    private Combattant combattant;
    private Combattant adversaire;
    private Boolean agro;

    public Combat(List<Combattant> list, Combattant combattant) {
        this.combattant = combattant;
        this.list = list;
        this.agro = false;
        Init();
        Agro();
        Attaque();

    }

    private void Init() {
        try {
            Thread.sleep(1000 / combattant.getInitVal());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void Agro() {
        if(agro) return;
        else if (combattant.getCaseDispo() != 4){
            System.out.println(String.format("%s est deja engagé", adversaire.getPrenom()));
            agro = true;
            return; 
        }
        while (!agro) {
            Random r = new Random();
            adversaire = list.get(r.nextInt(list.size()));
            if (adversaire.getCaseDispo() > 0) {
                System.out.println(
                    String.format("%s prend %s pour cible", combattant.getPrenom(), adversaire.getPrenom()));
                adversaire.fillCase();
                agro = true;
            }
        }
    }

    private void Attaque() {
        int degat = Degat();
        if(degat <= 0){
            System.out.println(String.format("%s n'inflige pas de degats", combattant.getPrenom()));
            return;
        }
        System.out.println(String.format("%s inflige %d de degats à %s", combattant.getPrenom(), degat, adversaire.getPrenom()));
        adversaire.setPVVal(adversaire.getPVVal() - degat);

    }

    private int Degat(){
        System.out.println(
                    String.format("%s attque %s", combattant.getPrenom(), adversaire.getPrenom()));
        Random r = new Random();
        int esquive = r.nextInt(101);
        if(esquive <= adversaire.getParadeVal()){
            System.out.println(String.format("%s esquive l'attaque", adversaire.getPrenom()));
            return 0;
        }

        int crit = r.nextInt(101);
        if (crit <= combattant.getCritVal())
        {
            System.out.println(String.format("%s fait un coup critique", combattant.getPrenom()));
            return combattant.getAtkVal();
        }
        return combattant.getAtkVal() - adversaire.getDefVal();
    }
}
