package masi.rpg.bll.services.interfaces;

import masi.rpg.model.DetailCombattant;
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.StatPerso;

public interface IStatService {
    /**
     * Insert in stat_perso table the sp's data
     * @param dc Data to import
     */
    public void InsertStatPerso(DetailCombattant dc);

    /**
     * Set ID perso in StatPerso model with Combattant ID
     * @param sp StatPerso model
     * @param c Combattant model
     */
    public void setIdPerso(StatPerso sp, Combattant c);

}
