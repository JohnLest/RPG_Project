package masi.rpg.bll.services.interfaces;

import masi.rpg.model.databaseModel.StatPerso;

public interface IStatService {
    /**
     * Insert in stat_perso table the sp's data
     * @param sp Data to import
     */
    public void InsertStatPerso(StatPerso sp);
}
