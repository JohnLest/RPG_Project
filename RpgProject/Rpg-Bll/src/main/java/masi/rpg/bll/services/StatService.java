package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;

import masi.rpg.bll.Mapping;
import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.dal.repositories.StatPersoRepo;
import masi.rpg.dal.repositories.interfaces.IStatPersoRepo;
import masi.rpg.model.DetailCombattant;
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.StatPerso;

public class StatService implements IStatService {
    private IStatPersoRepo statPersoRepo;

    public StatService(Connection connection) {
        this.statPersoRepo = new StatPersoRepo(connection);
    }

    //#region with Repo
    
    public void InsertStatPerso(DetailCombattant cb) {
        try {
            StatPerso sp = Mapping.dcombattantToStatPerso(cb);
            statPersoRepo.Insert(sp);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //#endregion

    public void setIdPerso(StatPerso sp, Combattant c){
        sp.setID_Perso(c.getID());
    }
}
