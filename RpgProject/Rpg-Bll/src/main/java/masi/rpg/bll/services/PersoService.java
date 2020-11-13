package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import masi.rpg.bll.Mapping;
import masi.rpg.bll.services.interfaces.IPersoService;
import masi.rpg.dal.repositories.CombatantRepo;
import masi.rpg.dal.repositories.NewPersoRepo;
import masi.rpg.dal.repositories.PersoRepo;
import masi.rpg.dal.repositories.interfaces.ICombatantRepo;
import masi.rpg.dal.repositories.interfaces.INewPersoRepo;
import masi.rpg.dal.repositories.interfaces.IPersoRepo;
import masi.rpg.model.databaseModel.Classe;
import masi.rpg.model.databaseModel.Combatant;
import masi.rpg.model.databaseModel.Perso;

public class PersoService implements IPersoService {
    private ICombatantRepo combatantRepo;
    private INewPersoRepo newPersoRepo;
    private IPersoRepo persoRepo;

    public PersoService(Connection connect) {
        this.combatantRepo = new CombatantRepo(connect);
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

    public List<Combatant> GetConbatantView() {
        try {
            List<Object> vueObj = combatantRepo.GetAllWhere("1 LIMIT 20");
            List<Combatant> vue = 
                vueObj.stream()
                    .map(obj -> Mapping.toCombatant(obj))
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
            Classe maclasse = Mapping.toClasse(combatantRepo.GetByID(4));
            System.out.println(maclasse.getNom_Classe());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void UpdateView(Combatant c, int id) {
        try {
            Perso perso = Mapping.combatantToPerso(c);
            String where = String.format("ID_Perso = %d", id);
            persoRepo.Update(perso, where);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
