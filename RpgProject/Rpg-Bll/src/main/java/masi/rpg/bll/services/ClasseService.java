package masi.rpg.bll.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import masi.rpg.bll.services.interfaces.IClasseService;
import masi.rpg.dal.repositories.ClasseRepo;
import masi.rpg.dal.repositories.interfaces.IClasseRepo;
import masi.rpg.model.databaseModel.Classe;

public class ClasseService implements IClasseService {
    private IClasseRepo classeRepo;

    public ClasseService(Connection connect) {
        this.classeRepo = new ClasseRepo(connect);
    }

    public void GetById(int id) throws Exception {
        try {
            Classe maclasse =  Classe.Format(classeRepo.GetByID(4));
            System.out.println(maclasse.getNom_Classe());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void GetColumns(){
        List<String> columns = new ArrayList<String>();
        columns.add("ID_Classe");
        columns.add("Nom_Classe");
        try {
            List<Object> lst = classeRepo.GetColumn(columns);
            List<Classe> lstC = lst.stream()
            .map(e -> Classe.Format(e))
            .collect(Collectors.toList());

            for (Classe classe : lstC) {
                System.out.println(classe.getNom_Classe());
            }
            System.out.println("stop");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
