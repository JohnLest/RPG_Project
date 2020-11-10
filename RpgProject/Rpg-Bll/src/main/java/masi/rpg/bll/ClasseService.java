package masi.rpg.bll;

import java.sql.Connection;
import java.sql.SQLException;
import masi.rpg.dal.*;

public class ClasseService {
    private ClasseRepo classeRepo;

    public ClasseService(Connection connect) {
        this.classeRepo = new ClasseRepo(connect);
    }

    public void getbyId(int id) {
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
