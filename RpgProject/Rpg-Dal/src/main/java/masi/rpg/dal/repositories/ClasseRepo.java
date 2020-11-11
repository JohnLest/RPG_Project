package masi.rpg.dal.repositories;

import masi.rpg.dal.repositories.interfaces.IClasseRepo;
import masi.rpg.model.databaseModel.Classe;

import java.sql.Connection;
import johnlest.tools.genericRepo.GenericRepo;

public class ClasseRepo extends GenericRepo implements IClasseRepo
{
    public ClasseRepo(Connection connection) {
        super("`rpg.classe`", Classe.class, connection);
    }
}