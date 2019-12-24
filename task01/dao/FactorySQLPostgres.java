package part01.lesson15.task01.dao;

import part01.lesson15.task01.work.FactoryUsers;
import part01.lesson15.task01.pojo.Role;
import part01.lesson15.task01.pojo.Users;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
class FactorySQLPostgres<T extends Users> {
    private String SQL_DATABASE_STATUS = "select exists(select * from " +
            "pg_catalog.pg_database where lower(datname) = lower(?))";
    private String SQL_DATABASE_CREATE = "create database ? " +
            "with template default encoding 'UTF-8'";
    private String CREATE_TABLE_USER = "CREATE TABLE if not exists public.user (" +
            "id bigserial primary key," +
            "name varchar(150) NOT NULL," +
            "birthday varchar(15)," +
            "login_ID bigint NOT NULL," +
            "city varchar(100)," +
            "email varchar(100) NOT NULL," +
            "description varchar(200));";
    private String DELETE_TABLE_USER = "DROP TABLE public.user;";
    private String CREATE_TABLE_ROLE = "CREATE TABLE if not exists public.role (" +
            "id bigserial primary key," +
            "name varchar(100) NOT NULL," +
            "description varchar(200));";
    private String DELETE_TABLE_ROLE = "DROP TABLE public.role;";
    private String CREATE_TABLE_USER_ROLE = "CREATE TABLE if not exists public.user_role (" +
            "id bigserial primary key," +
            "user_id bigint NOT NULL," +
            "role_id bigint NOT NULL);";
    private String DELETE_TABLE_USER_ROLE = "DROP TABLE public.user_role;";
    private String INSERT_USER = "INSERT INTO public.user " +
            "(name,birthday, login_id, city, email, description)" +
            " VALUES (?,?,?,?,?,?);";
    private String INSERT_ROLE = "INSERT INTO public.role " +
            "(name,  description)" +
            " VALUES (?,?);";
    private String INSERT_USER_ROLE = "INSERT INTO public.user_role " +
            "(user_id,  role_id)" +
            " VALUES (?,?);";
    private String SELECT_USER_NAME = "SELECT u.id,u.name, u.birthday,u.login_id," +
            " u.city, u.email, u.description, (r.name) AS role FROM public.user AS u\n" +
            "    LEFT JOIN public.user_role AS ur ON   u.id = ur.user_id\n" +
            "    LEFT JOIN role AS r on ur.role_id = r.id\n" +
            "WHERE u.name = ?;";
    private String SELECT_USER_NAME_LIGINID = "SELECT u.id,u.name, u.birthday,u.login_id," +
            " u.city, u.email, u.description, (r.name) AS role FROM public.user AS u\n" +
            "    LEFT JOIN public.user_role AS ur ON   u.id = ur.user_id\n" +
            "    LEFT JOIN role AS r on ur.role_id = r.id\n" +
            "WHERE u.name = ? AND u.login_ID = ?;";
    private String SELECT_ALL_USER = "SELECT u.id,u.name, u.birthday,u.login_id, u.city, u.email, u.description, (r.name) AS role FROM public.user AS u\n" +
            "    LEFT JOIN public.user_role AS ur ON   u.id = ur.user_id\n" +
            "    LEFT JOIN role AS r on ur.role_id = r.id;";
    private String SELECT_ROLE = "SELECT distinct id,name, description FROM public.role WHERE name = ?;";
    private String SELECT_USER_ROLE = "SELECT id,user_id, role_id FROM public.user_role;";
    private String IS_TABLE = "SELECT * FROM information_schema.tables WHERE TABLE_NAME = ?;";

    /**
     * sql request
     * @return String sql
     */
    protected String isTable(){
        return IS_TABLE;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getSqlDatabaseStatus() {
        return SQL_DATABASE_STATUS;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getSqlDatabaseCreate() {
        return SQL_DATABASE_CREATE;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getDELETE_TABLE(String nameTable) {
        switch (nameTable) {
            case "USER":
                return DELETE_TABLE_USER;
            case "ROLE":
                return DELETE_TABLE_ROLE;
            case "USER_ROLE":
                return DELETE_TABLE_USER_ROLE;
        }
        return null;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getCREATE_TABLE(String nameTable) {
        switch (nameTable) {
            case "USER":
                return CREATE_TABLE_USER;
            case "ROLE":
                return CREATE_TABLE_ROLE;
            case "USER_ROLE":
                return CREATE_TABLE_USER_ROLE;
        }
        return null;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getINSERT_USER() {
        return INSERT_USER;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getINSERT_ROLE() {
        return INSERT_ROLE;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getINSERT_USER_ROLE() {
        return INSERT_USER_ROLE;
    }

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param user Object implements User
     * @throws SQLException
     */
    protected void setValues(PreparedStatement ps, T user) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getBirthday());
        ps.setBigDecimal(3, user.getLogin_id());
        ps.setString(4, user.getCity());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getDescription());
    }

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param idUser id user
     * @param idRole id role
     * @throws SQLException
     */
    protected void setValues(PreparedStatement ps, BigDecimal idUser, BigDecimal idRole) throws SQLException {
        ps.setBigDecimal(1, idUser);
        ps.setBigDecimal(2, idRole);
    }

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values String
     * @param Login_id BigDecimal
     * @throws SQLException
     */
    protected void setValues(PreparedStatement ps, String values, BigDecimal Login_id) throws SQLException {
        ps.setString(1, values);
        ps.setBigDecimal(2, Login_id);
    }

    /***
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values String
     * @throws SQLException
     */
    protected void setValues(PreparedStatement ps, String values) throws SQLException {
        ps.setString(1, values);
    }

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values1 String
     * @param values2 String
     * @throws SQLException
     */
    protected void setValues(PreparedStatement ps, String values1, String values2) throws SQLException {
        ps.setString(1, values1);
        ps.setString(2, values2);
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getSelectByNameUser() {
        return SELECT_USER_NAME;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getSelectByNameLoginidUser() {
        return SELECT_USER_NAME_LIGINID;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getSelectAllUsers() {
        return SELECT_ALL_USER;
    }

    /**
     * sql request
     * @return String sql
     */
    protected String getSelectRole() {
        return SELECT_ROLE;
    }

    /**
     *
     * @param rs ResultSet
     * @param factoryUsers factory users
     * @return Object implements Users
     * @throws SQLException
     */
    protected Users getUser(ResultSet rs, FactoryUsers factoryUsers) throws SQLException {
        Users user = factoryUsers.newInstance(rs.getString("name"));
        user.setLogin_id(rs.getBigDecimal("login_id"));
        user.setCity(rs.getString("city"));
        user.setEmail(rs.getString("email"));
        user.setDescription(rs.getString("description"));
        user.setBirthday(rs.getString("birthday"));
        user.setRole(Role.getEnumByNameRole(rs.getString("role")));
        return user;
    }
}
