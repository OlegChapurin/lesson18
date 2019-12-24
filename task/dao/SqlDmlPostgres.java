package lesson.task.dao;

import lesson.task.work.FactoryUsers;
import lesson.task.pojo.Role;
import lesson.task.pojo.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public class SqlDmlPostgres<T extends Users> implements SqlDml<T> {

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

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getINSERT_USER() {
        return INSERT_USER;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getINSERT_ROLE() {
        return INSERT_ROLE;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getINSERT_USER_ROLE() {
        return INSERT_USER_ROLE;
    }

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param user Object implements User
     * @throws SQLException
     */
    @Override
    public void setValues(PreparedStatement ps, T user) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getBirthday());
        ps.setLong(3, user.getLogin_id());
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
    @Override
    public void setValues(PreparedStatement ps, long idUser, long idRole) throws SQLException {
        ps.setLong(1, idUser);
        ps.setLong(2, idRole);
    }

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values String
     * @param Login_id long
     * @throws SQLException
     */
    @Override
    public void setValues(PreparedStatement ps, String values, long Login_id) throws SQLException {
        ps.setString(1, values);
        ps.setLong(2, Login_id);
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

    /**
     * fill in values PreparedStatement
     * @param ps PreparedStatement
     * @param values1 String
     * @param values2 String
     * @throws SQLException
     */
    @Override
    public void setValues(PreparedStatement ps, String values1, String values2) throws SQLException {
        ps.setString(1, values1);
        ps.setString(2, values2);
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getSelectByNameUser() {
        return SELECT_USER_NAME;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getSelectByNameLoginIdUser() {
        return SELECT_USER_NAME_LIGINID;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getSelectAllUsers() {
        return SELECT_ALL_USER;
    }

    /**
     * sql request
     * @return String sql
     */
    @Override
    public String getSelectRole() {
        return SELECT_ROLE;
    }

    /**
     *
     * @param rs ResultSet
     * @param factoryUsers factory users
     * @return Object implements Users
     * @throws SQLException
     */
    @Override
    public T getUser(ResultSet rs, FactoryUsers factoryUsers) throws SQLException {
        T user = (T) factoryUsers.newInstance(rs.getString("name"));
        user.setLogin_id(rs.getLong("login_id"));
        user.setCity(rs.getString("city"));
        user.setEmail(rs.getString("email"));
        user.setDescription(rs.getString("description"));
        user.setBirthday(rs.getString("birthday"));
        user.setRole(Role.getEnumByNameRole(rs.getString("role")));
        return user;
    }
}
