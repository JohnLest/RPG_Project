package masi.rpg.dal;

import masi.rpg.database.*;

import java.sql.Connection;

import johnlest.tools.*;

public class ClasseRepo extends GenericRepo
{
    public ClasseRepo(Connection connection) {
        super("`rpg.classe`", connection);
    }
}