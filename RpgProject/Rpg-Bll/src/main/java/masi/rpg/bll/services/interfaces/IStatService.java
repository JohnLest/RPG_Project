package masi.rpg.bll.services.interfaces;

import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.StatEquipe;
import masi.rpg.model.databaseModel.StatPerso;

public interface IStatService {
    /**
     * Insert in stat_perso table the sp's data
     * @param dc Data to import
     */
    public void InsertStatPerso(StatPerso sp);

    /**
     * Insert in stat_equipe table the se's data
     * @param se Data to import
     * @return primary key generate
     */
    public int InsertStatEquipe(StatEquipe se);

    /**
     * Update StatEquipe after combat
     * @param se StatEquipe
     */
    public void updateStatEquipe(StatEquipe se, int survivant);

    /**
     * Set ID perso in StatPerso model with Combattant ID
     * @param sp StatPerso model
     * @param c Combattant model
     */
    public void setId(StatPerso sp, StatEquipe se, Combattant c);

    /**
     * Update totalDegat
     * @param se StatEquipe
     * @param degat value
     */
    public void addDegat(StatEquipe se, int degat);

    /**
     * Update totalAtk
     * @param se StatEquipe
     * @param atk value
     */
    public void addAtk(StatEquipe se, int atk);

    /**
     * Updtate totalSoins
     * @param se StatEquipe
     * @param soins value
     */
    public void addSoins(StatEquipe se, int soins );

    /**
     * Update totalSurvivant
     * @param se StatEquipe
     * @param survivant value
     */
    public void updateSurvivant(StatEquipe se, short survivant);
}
