package masi.rpg.app;

import java.sql.Connection;
import masi.rpg.bll.services.*;
import masi.rpg.bll.services.interfaces.*;

public class PersoControler {

    private IClasseService classeService;

    public PersoControler(Connection connect) {
        this.classeService = new ClasseService(connect);
        PersoControler();
    }

    private void PersoControler(){
        classeService.GetById(3);
        return;
    }
}
