package masi.rpg.dal.repositories;

import java.sql.Connection;
import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.ICombattantRepo;
import masi.rpg.model.databaseModel.Combattant;

public class CombattantRepo extends GenericRepo implements ICombattantRepo{
    public CombattantRepo(Connection connection) {
        super("combattant", Combattant.class, connection);
    }
}
