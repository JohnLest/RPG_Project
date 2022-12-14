package masi.rpg.bll.services.interfaces;

import java.util.List;

import masi.rpg.model.DetailCombattant;
import masi.rpg.model.Equipe;
import masi.rpg.model.databaseModel.Combattant;

public interface IPersoService {

    //#region With Repo
    /**
     * Launch newPerso() stored procedure
     */
    public void CreateNewPerso();

    /**
     * Get Combattant view Limit 20
     * @return View
     */
    public List<Combattant> GetCombattantView();
    
    /**
     * Get Row by ID 
     * @param id Value of the primary key
     */
    public void GetById(int id);

    /**
     * Update Conbatant view
     * @param update CombattantBean
     * @param id Id to update
     */
    public void UpdateView(Combattant c, int id);

    //#endregion

    /**
     * Prepare the teams
     * @param equipe
     */
    public Equipe SetEquipe(List<Combattant> equipe);

    /**
     * Update the life points's combattant
     * @param c
     * @param degats
     */
    public void UpdatePVValue(DetailCombattant c,  int degats);

    /**
     * Check is the first combat
     * @param c
     * @return
     */
    public boolean IsFirstCombat(DetailCombattant c);

    
}
