package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;
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
import masi.rpg.model.databaseModel.Classe;
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

    public void CreateNewPerso() {
        try {
            newPersoRepo.UseStorProc("newPerso()");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Combattant> GetConbatantView() {
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
            Classe maclasse = Mapping.toClasse(combattantRepo.GetByID(4));
            System.out.println(maclasse.getNom_Classe());
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
}
