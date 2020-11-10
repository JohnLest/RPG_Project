package masi.rpg.bll.services.interfaces;

public interface IClasseService {
    /**
     * Get Row by ID 
     * @param id Value of the primary key
     */
    public void GetById(int id);
    
    public void GetColumns();
}
