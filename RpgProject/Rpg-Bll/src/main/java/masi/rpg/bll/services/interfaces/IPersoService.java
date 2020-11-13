package masi.rpg.bll.services.interfaces;

import java.util.List;
import masi.rpg.model.databaseModel.Combatant;

public interface IPersoService {

    /**
     * Launch newPerso() stored procedure
     */
    public void CreateNewPerso();

    /**
     * Get Combatant view Limit 20
     * @return View
     */
    public List<Combatant> GetConbatantView();
    
    /**
     * Get Row by ID 
     * @param id Value of the primary key
     */
    public void GetById(int id);

    /**
     * Update Conbatant view
     * @param update CombatantBean
     * @param id Id to update
     */
    public void UpdateView(Combatant c, int id);
    
}
