package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import masi.rpg.bll.Mapping;
import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.dal.repositories.CombattantRepo;
import masi.rpg.dal.repositories.NewPersoRepo;
import masi.rpg.dal.repositories.PersoRepo;
import masi.rpg.dal.repositories.interfaces.ICombattantRepo;
import masi.rpg.dal.repositories.interfaces.INewPersoRepo;
import masi.rpg.dal.repositories.interfaces.IPersoRepo;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.Perso;
import masi.rpg.model.databaseModel.StatEquipe;
import masi.rpg.model.databaseModel.StatPerso;

public class PersoService implements IPersoService {
    private ICombattantRepo combattantRepo;
    private INewPersoRepo newPersoRepo;
    private IPersoRepo persoRepo;

    public PersoService(Connection connect) {
        this.combattantRepo = new CombattantRepo(connect);
        this.newPersoRepo = new NewPersoRepo(connect);
        this.persoRepo = new PersoRepo(connect);
    }

    //#region With Repo
    public void CreateNewPerso() {
        try {
            newPersoRepo.UseStorProc();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Combattant> GetCombattantView() {
        try {
            List<Object> vueObj = combattantRepo.GetAllWhere("1 LIMIT 20");
            List<Combattant> vue = 
                vueObj.stream()
                    .map(obj -> Mapping.toCombattant(obj))
                    .collect(Collectors.toList());
            return vue;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void GetById(int id) {
        try {
            Perso p = Mapping.toPerso(persoRepo.GetByID(id));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void UpdateView(Combattant c, int id) {
        try {
            c.setNbrCombat(c.getNbrCombat() + 1);
            Perso perso = Mapping.combattantToPerso(c);
            String where = String.format("ID_Perso = %d", id);
            persoRepo.Update(perso, where);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //#endregion

    public Equipe SetEquipe(List<Combattant> equipe) {
        Equipe _equipe = new Equipe();
        _equipe.setStatEquipe(new StatEquipe());
        List<DetailCombattant> dcLst = new ArrayList();
        for (Combattant combattant : equipe) {
            DetailCombattant dc = new DetailCombattant();
            dc.setStatPerso(new StatPerso());
            dc.setCombattant(combattant);
            dc.setCaseContact(new ArrayList());
            dcLst.add(dc);

            if(combattant.getClasse().equals("Guerrier")) _equipe.getStatEquipe().incremanteNbrGuerriers();
            else if (combattant.getClasse().equals("Mage")) _equipe.getStatEquipe().incremanteNbrMage();
            else if (combattant.getClasse().equals("Prêtre")) _equipe.getStatEquipe().incremanteNbrPretre();
            else if (combattant.getClasse().equals("Voleur")) _equipe.getStatEquipe().incremanteNbrVoleurs();

            _equipe.getStatEquipe().setTotalPV(_equipe.getStatEquipe().getTotalPV() + combattant.getPVVal());
        }
        _equipe.setDetailCombattant(dcLst);

        return _equipe;
    }

    public void UpdatePVValue(DetailCombattant c,  int degats){
        if(degats == 0) c.getCombattant().setPVVal(0);
        else if(degats > 0)
        {
            c.getCombattant().setPVVal(c.getCombattant().getPVVal() - degats);
            c.getStatPerso().setPVPerdu(c.getStatPerso().getPVPerdu() + degats);
        }
        else if(degats < 0){
            c.getCombattant().setPVVal(c.getCombattant().getPVVal() - degats);
            c.getStatPerso().setPVGagner(c.getStatPerso().getPVGagner() - degats);
        }
    }

    public boolean IsFirstCombat(DetailCombattant c){
        if(c.getCombattant().getNbrCombat() == 0){
            c.getStatPerso().setIsFirstCombat((short)1);
            return true;
        }
        else{
            c.getStatPerso().setIsFirstCombat((short)0);
            return false;
        }
    }
}
