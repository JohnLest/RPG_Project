package masi.rpg.bll.services.interfaces;

import java.util.List;

import masi.rpg.model.DetailCombattant;
import masi.rpg.model.databaseModel.Combattant;

public interface IPersoService {

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
     * Prepare the teams
     * @param equipe
     * @param nom
     */
    public List<DetailCombattant> SetEquipe(List<Combattant> equipe, char nom);

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
    
}
