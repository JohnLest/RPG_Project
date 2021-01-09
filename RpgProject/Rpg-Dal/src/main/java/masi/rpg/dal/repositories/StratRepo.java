package masi.rpg.dal.repositories;

import java.sql.Connection;

import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.IStratRepo;
import masi.rpg.model.databaseModel.Strat;

public class StratRepo extends GenericRepo implements IStratRepo {
    public StratRepo(Connection connection) {
        super("strat", Strat.class, connection);
    }
    
}
