package part01.lesson15.task01.dao;

import part01.lesson15.task01.connection.ConnectionJdbc;
import part01.lesson15.task01.connection.ConnectionManagerPostgres;
import part01.lesson15.task01.pojo.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Oleg_Chapurin
 */
public class DatabasePostgres<T extends Users> implements Database<T> {
    private ConnectionJdbc connectionJdbc =
            ConnectionManagerPostgres.getInstance();
    private Connection connection;
    private FactorySQLPostgres<T> factorySQL = new FactorySQLPostgres<>();

    /**
     * @param url  Database URL
     * @param user Database login
     * @param password  Database password
     */
    public DatabasePostgres(String url,String user, String password){
        this.connection = connectionJdbc.getConnection(url, user, password);
    }

    /**
     * Close connection JDBC
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param nameDatabase name database
     * @return Database status YES,NOT,ERROR
     */
    protected DatabaseStatus isDatabase(String nameDatabase) {
        DatabaseStatus status = DatabaseStatus.ERROR;
        String SqlDatabaseStatus = factorySQL.getSqlDatabaseStatus();
        try (PreparedStatement ps = connection.prepareStatement(SqlDatabaseStatus);) {
            ps.setString(1, nameDatabase);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (String.valueOf(rs.getArray(1)).equals("t")) {
                status = DatabaseStatus.YES;
            } else {
                status = DatabaseStatus.NOT;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     *
     * @param nameDatabase name database
     * @return boolean creat database true false
     */
    @Override
    public boolean creatDatabase(String nameDatabase) {
        if(DatabaseStatus.NOT == isDatabase(nameDatabase)) {
            String SqlDatabaseCreate = factorySQL.getSqlDatabaseCreate();
            SqlDatabaseCreate = SqlDatabaseCreate.replace("?", nameDatabase);
            try (PreparedStatement ps = connection.prepareStatement(SqlDatabaseCreate);) {
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
