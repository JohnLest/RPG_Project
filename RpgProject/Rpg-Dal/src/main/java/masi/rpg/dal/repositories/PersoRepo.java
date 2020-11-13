package masi.rpg.dal.repositories;

import java.sql.Connection;
import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.IPersoRepo;
import masi.rpg.model.databaseModel.Perso;

public class PersoRepo extends GenericRepo implements IPersoRepo {
    public PersoRepo(Connection connection) {
        super("perso", Perso.class, connection);
    }
    
}
