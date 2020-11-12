package masi.rpg.dal.repositories;

import java.sql.Connection;
import johnlest.tools.genericRepo.GenericRepo;
import masi.rpg.dal.repositories.interfaces.ICombatantRepo;
import masi.rpg.model.databaseModel.Combatant;

public class CombatantRepo extends GenericRepo implements ICombatantRepo{
    public CombatantRepo(Connection connection) {
        super("combatant", Combatant.class, connection);
    }
}
