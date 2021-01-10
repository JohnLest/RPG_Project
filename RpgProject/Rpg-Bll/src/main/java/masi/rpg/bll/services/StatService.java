package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;

import masi.rpg.bll.Mapping;
import masi.rpg.bll.services.interfaces.IStatService;
import masi.rpg.dal.repositories.StatEquipeRepo;
import masi.rpg.dal.repositories.StatPersoRepo;
import masi.rpg.dal.repositories.interfaces.IStatEquipeRepo;
import masi.rpg.dal.repositories.interfaces.IStatPersoRepo;
import masi.rpg.model.databaseModel.Combattant;
import masi.rpg.model.databaseModel.StatEquipe;
import masi.rpg.model.databaseModel.StatPerso;

public class StatService implements IStatService {
    private IStatPersoRepo statPersoRepo;
    private IStatEquipeRepo statEquipeRepo;

    public StatService(Connection connection) {
        this.statPersoRepo = new StatPersoRepo(connection);
        this.statEquipeRepo = new StatEquipeRepo(connection);
    }

    //#region with Repo
    
    public void InsertStatPerso(StatPerso sp) {
        try {
            statPersoRepo.Insert(sp);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int InsertStatEquipe(StatEquipe se){
        try {
            int key = Integer.parseInt(statEquipeRepo.Insert(se).toString());
            se.setID_StatEquipe(key);
            return key;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public void updateStatEquipe(StatEquipe se, int survivant){
        try {
            se.setNbrSurvivant((short) survivant);
            String where = String.format("ID_StatEquipe = %d", se.getID_StatEquipe());
            statEquipeRepo.Update(se, where);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //#endregion

    //#region stat_equipe
    public void setId(StatPerso sp, StatEquipe se, Combattant c){
        sp.setID_Perso(c.getID());
        sp.setID_Stat_Equipe(se.getID_StatEquipe());
    }

    public void addDegat(StatEquipe se, int degat){
        se.setTotalDegats(se.getTotalDegats() + degat);
    }

    public void addAtk(StatEquipe se, int atk){
        se.setTotalAtk(se.getTotalAtk() + atk);
    }

    public void addSoins(StatEquipe se, int soins ){
        se.setTotalSoins(se.getTotalSoins() + soins);
    }

    public void updateSurvivant(StatEquipe se, short survivant){
        se.setNbrSurvivant(survivant);
    }
    //#endregion

}
