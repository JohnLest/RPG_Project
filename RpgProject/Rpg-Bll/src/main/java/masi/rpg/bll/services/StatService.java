package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;

import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.dal.repositories.StatPersoRepo;
import masi.rpg.dal.repositories.interfaces.IStatPersoRepo;
import masi.rpg.model.databaseModel.StatPerso;

public class StatService implements IStatService {
    private IStatPersoRepo statPersoRepo;

    public StatService(Connection connection) {
        this.statPersoRepo = new StatPersoRepo(connection);
    }

    public void InsertStatPerso(StatPerso sp) {
        try {
            statPersoRepo.Insert(sp);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
