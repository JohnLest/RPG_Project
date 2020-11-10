package masi.rpg.app;

import java.sql.Connection;
import masi.rpg.bll.ClasseService;

public class PersoControler {

    private ClasseService classeService;

    public PersoControler(Connection connect) {
        this.classeService = new ClasseService(connect);
        PersoControler();
    }

    private void PersoControler(){
        classeService.getbyId(3);
        return;
    }
}
