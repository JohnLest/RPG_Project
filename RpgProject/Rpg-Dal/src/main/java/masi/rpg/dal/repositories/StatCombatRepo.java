package masi.rpg.dal.repositories;

import java.sql.Connection;

import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.IStatCombatRepo;
import masi.rpg.model.databaseModel.StatCombat;

public class StatCombatRepo extends GenericRepo implements IStatCombatRepo {
    public StatCombatRepo(Connection connection) {
        super("stat_combat", StatCombat.class, connection);
    }
    
}
