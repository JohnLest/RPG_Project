package masi.rpg.dal.repositories;

import java.sql.Connection;

import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.IStatEquipeRepo;
import masi.rpg.model.databaseModel.StatEquipe;

public class StatEquipeRepo extends GenericRepo implements IStatEquipeRepo{
    public StatEquipeRepo(Connection connection) {
        super("stat_equipe", StatEquipe.class, connection);
    }
}
