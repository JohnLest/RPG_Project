package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;

import masi.rpg.bll.Mapping;
import masi.rpg.bll.services.interfaces.IStratService;
import masi.rpg.dal.repositories.StratRepo;
import masi.rpg.dal.repositories.interfaces.IStratRepo;
import masi.rpg.model.databaseModel.Strat;

public class StratService implements IStratService {
    private IStratRepo stratRepo;

    public StratService(Connection connection) {
        this.stratRepo = new StratRepo(connection);
    }

    public Strat getStrat() {
        try {
            Strat strat = Mapping.toStrat(stratRepo.GetOneRandom());
            return strat;
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
