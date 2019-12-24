package lesson.task.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public class SqlDdlPostgres implements SqlDdl{
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
    private String CREATE_TABLE_LOGS = "CREATE TABLE if not exists public.logs " +
            "(id bigserial primary key,date timestamp NOT NULL,log_level varchar(5) NOT NULL,\n"+
            "message varchar(200)  NOT NULL,exception varchar(200));";
    private String DELETE_TABLE_LOGS = "DROP TABLE public.logs;";
    private String IS_TABLE = "SELECT * FROM information_schema.tables WHERE TABLE_NAME = ?;";

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String isTable(){
        return IS_TABLE;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getSqlDatabaseStatus() {
        return SQL_DATABASE_STATUS;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getSqlDatabaseCreate() {
        return SQL_DATABASE_CREATE;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getDELETE_TABLE(String nameTable) {
        switch (nameTable) {
            case "USER":
                return DELETE_TABLE_USER;
            case "ROLE":
                return DELETE_TABLE_ROLE;
            case "USER_ROLE":
                return DELETE_TABLE_USER_ROLE;
            case "LOGS":
                return DELETE_TABLE_LOGS;
        }
        return null;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getCREATE_TABLE(String nameTable) {
        switch (nameTable) {
            case "USER":
                return CREATE_TABLE_USER;
            case "ROLE":
                return CREATE_TABLE_ROLE;
            case "USER_ROLE":
                return CREATE_TABLE_USER_ROLE;
            case "LOGS":
                return CREATE_TABLE_LOGS;
        }
        return null;
    }

    /***
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values String
     * @throws SQLException
     */
    @Override
    public void setValues(PreparedStatement ps, String values) throws SQLException {
        ps.setString(1, values);
    }
}
