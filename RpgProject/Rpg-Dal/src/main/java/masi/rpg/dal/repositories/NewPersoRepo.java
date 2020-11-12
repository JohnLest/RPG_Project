package masi.rpg.dal.repositories;

import java.sql.Connection;
import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.INewPersoRepo;

public class NewPersoRepo extends GenericRepo implements INewPersoRepo{
    public NewPersoRepo(Connection connection) {
        super(connection);
    }
}
