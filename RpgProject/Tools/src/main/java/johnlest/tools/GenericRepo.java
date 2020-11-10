package johnlest.tools;

import java.sql.*;
import java.util.*;

/**
 * GenericRepo
 */
public class GenericRepo {

    protected String table;
    protected Connection connection;

    /**
     * Constructor
     * @param table
     * @param connection
     */
    public GenericRepo(String table, Connection connection) {
        this.table = table;
        this.connection = connection;
    }

    //#region Public Methode
    /**
     * Get Row by ID 
     * @param id primary Key value
     * @return objects list
     * @throws SQLException
     */
    public List<Object> GetByID(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = %d ;", table, GetPrimaryKey(), id);
        return ResultRow(ExecuteQuery(query));
    }
    /**
     * Get all table
     * @return Objects table
     * @throws SQLException
     */
    public List<List<Object>> GetAll() throws SQLException {
        String query = String.format("SELECT * FROM %s ;", table);
        return ResultTable(ExecuteQuery(query));
    }
    /**
     * Get all table with condition
     * @param where Condition 
     * @return Objects table
     * @throws SQLException
     */
    public List<List<Object>> GetAllWhere(String where) throws SQLException{
        String query = String.format("SELECT * FROM %s WHERE %s ;", table, where);
        return ResultTable(ExecuteQuery(query));
    }
    /**
     * Get the first row with condition
     * @param where Condition
     * @return Objexts List
     * @throws SQLException
     */
    public List<Object> GetFirst(String where) throws SQLException{
        String query = String.format("SELECT * FROM %s WHERE %s LIMIT 1 ;", table, where);
        return ResultRow(ExecuteQuery(query));
    }
    /**
     * Get some elements in the first row with condition
     * @param elem Column list tu search
     * @param where Condition
     * @return Object List
     * @throws SQLException
     */
    public List<Object> GetFirst(List<String> elem, String where) throws SQLException{
        String query = String.format("SELECT * FROM %s WHERE %s LIMIT 1 ;", table, where);
        return ResultRow(ExecuteQuery(query));
    }
    /**
     * Count number of row in table
     * @return Number
     * @throws SQLException
     */
    public int Count() throws SQLException{
        String query = String.format("SELECT COUNT (*) FROM %s ;", table);
        Object res = ResultRow(ExecuteQuery(query)).stream().findFirst();
        return (Tools.isNullOrEmpty(res)? 0 : (Integer)res);
    }
    /**
     * Count number of row in table with condition 
     * @param where Condition
     * @return Number
     * @throws SQLException
     */
    public int Count(String where) throws SQLException{
        String query = String.format("SELECT COUNT (*) FROM %s WHERE %s ;", table, where);
        Object res = ResultRow(ExecuteQuery(query)).stream().findFirst();
        return (Tools.isNullOrEmpty(res)? 0 : (Integer)res);
    }
    //#endregion

    //#region private Methode
    /**
     * Get name of the primary key 
     * @return PrimaryKey Name
     * @throws SQLException
     */
    private String GetPrimaryKey() throws SQLException {
        String query = String.format("SHOW keys FROM %s WHERE key_name = 'PRIMARY' ;", table);
        ResultSet result = ExecuteQuery(query);
        result.next();
        return result.getString("Column_name");
    }
    /**
     * Execute the query
     * @param query
     * @return Statement
     * @throws SQLException
     */
    private ResultSet ExecuteQuery(String query) throws SQLException{
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    /**
     * Return the result to a table
     * @param result
     * @return Objects Table
     * @throws SQLException
     */
    private List<List<Object>> ResultTable(ResultSet result) throws SQLException {
        List<List<Object>> resultList = new ArrayList<List<Object>>();
        while (result.next()) {
            resultList.add(GetRow(result));
        }
        return resultList;
    }
    /**
     * Return the result to a list
     * @param result
     * @return Objects List
     * @throws SQLException
     */
    private List<Object> ResultRow(ResultSet result) throws SQLException{
        return (result.next()? GetRow(result) : null);
    }
    /**
     * Get the rows of the result
     * @param result
     * @return Row (Objects List)
     * @throws SQLException
     */
    private List<Object> GetRow(ResultSet result) throws SQLException {
        List<Object> row = new ArrayList<Object>();
        ResultSetMetaData resultMD = result.getMetaData();
        for (int i = 1; i <= resultMD.getColumnCount(); i++) {
            row.add(result.getObject(resultMD.getColumnName(i)));
        }
        return row;
    }
    //#endregion
}