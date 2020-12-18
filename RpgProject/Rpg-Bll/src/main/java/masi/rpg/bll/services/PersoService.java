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
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.Perso;

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
            Perso perso = Mapping.combattantToPerso(c);
            String where = String.format("ID_Perso = %d", id);
            persoRepo.Update(perso, where);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //#endregion

    public List<DetailCombattant> SetEquipe(List<Combattant> equipe, char nom) {
        List<DetailCombattant> _equipe = new ArrayList();
        for (Combattant combattant : equipe) {
            DetailCombattant dc = new DetailCombattant();
            dc.setCombattant(combattant);
            dc.setEquipe(nom);
            dc.setCaseContact(new ArrayList());
            _equipe.add(dc);
        }
        return _equipe;
    }

    public void UpdatePVValue(Combattant c,  int degats){
        if(degats == -1) c.setPVVAL(0);
        c.setPVVal(c.getPVVal() - degats);
    }
}
