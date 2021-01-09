package masi.rpg.dal.repositories;

import java.sql.Connection;

import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.IStatPersoRepo;
import masi.rpg.model.databaseModel.StatPerso;

public class StatPersoRepo extends GenericRepo implements IStatPersoRepo {
    public StatPersoRepo(Connection connection) {
        super("stat_perso", StatPerso.class, connection);
    }
}
