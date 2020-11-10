package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;

import masi.rpg.bll.services.interfaces.IClasseService;
import masi.rpg.dal.repositories.ClasseRepo;
import masi.rpg.dal.repositories.interfaces.IClasseRepo;

public class ClasseService implements IClasseService {
    private IClasseRepo classeRepo;

    public ClasseService(Connection connect) {
        this.classeRepo = new ClasseRepo(connect);
    }

    public void GetById(int id) {
        try {
            for (Object elem : classeRepo.GetByID(id)) {
                System.out.println(elem);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
