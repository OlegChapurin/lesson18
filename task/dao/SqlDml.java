package lesson.task.dao;

import lesson.task.pojo.User;
import lesson.task.work.FactoryUsers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public interface SqlDml<T> {

    /**
     * sql request
     * @return String sql
     */
     String getINSERT_USER();

    /**
     * sql request
     * @return String sql
     */
     String getINSERT_ROLE();

    /**
     * sql request
     * @return String sql
     */
     String getINSERT_USER_ROLE();

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param user Object implements User
     * @throws SQLException
     */
     void setValues(PreparedStatement ps, T user) throws SQLException;

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param idUser id user
     * @param idRole id role
     * @throws SQLException
     */
     void setValues(PreparedStatement ps, long idUser, long idRole) throws SQLException ;

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values String
     * @param Login_id long
     * @throws SQLException
     */
     void setValues(PreparedStatement ps, String values, long Login_id) throws SQLException ;

    /***
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values String
     * @throws SQLException
     */
     void setValues(PreparedStatement ps, String values) throws SQLException;

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values1 String
     * @param values2 String
     * @throws SQLException
     */
     void setValues(PreparedStatement ps, String values1, String values2) throws SQLException;

    /**
     * sql request
     * @return String sql
     */
     String getSelectByNameUser();

    /**
     * sql request
     * @return String sql
     */
     String getSelectByNameLoginIdUser();

    /**
     * sql request
     * @return String sql
     */
     String getSelectAllUsers();

    /**
     * sql request
     * @return String sql
     */
     String getSelectRole();

    /**
     *
     * @param rs ResultSet
     * @param factoryUsers factory users
     * @return Object implements Users
     * @throws SQLException
     */
     T getUser(ResultSet rs, FactoryUsers factoryUsers) throws SQLException;
}
