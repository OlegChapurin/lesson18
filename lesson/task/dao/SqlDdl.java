package lesson.task.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public interface SqlDdl {

    /**
     * sql request
     * @return String sql
     */
    String isTable();

    /**
     * sql request
     * @return String sql
     */
    String getSqlDatabaseStatus();

    /**
     * sql request
     * @return String sql
     */
    String getSqlDatabaseCreate() ;

    /**
     * sql request
     * @return String sql
     */
    String getDELETE_TABLE(String nameTable) ;

    /**
     * sql request
     * @return String sql
     */
    String getCREATE_TABLE(String nameTable);

    /***
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values String
     * @throws SQLException
     */
    void setValues(PreparedStatement ps, String values) throws SQLException;
}
