package lesson.task.dao;

import lesson.task.work.FactoryUsers;
import lesson.task.connection.ConnectionJdbc;
import lesson.task.connection.ConnectionManagerPostgres;
import lesson.task.pojo.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.*;

/**
 * @author Oleg_Chapurin
 */
public class TableInputPostgres<T extends Users> implements TableDml<T> {

    private static Logger logger;
    private ConnectionJdbc connectionJdbc =
            ConnectionManagerPostgres.getInstance();
    private Connection connection;
    private SqlDml<T> factorySQL;

    /**
     * @param url  Database URL
     * @param user Database login
     * @param password  Database password
     */
    public TableInputPostgres(String url, String user, String password) {
        this.connection = connectionJdbc.getConnection(url, user, password);
        this.factorySQL = new SqlDmlPostgres<>();
        this.logger = LogManager.getLogger(TableInputPostgres.class);
    }

    public TableInputPostgres(Connection connection, SqlDml<T> factorySQL,Logger logger){
        this.connection = connection;
        this.factorySQL = factorySQL;
        this.logger = logger;
    }

    /**
     * commit if AutoCommit == false
     * @throws SQLException
     */
    private void commit() throws SQLException {
        if (connection.getAutoCommit() == false) {
            connection.commit();
        }
    }

    /**
     * rollback if AutoCommit == false
     * @throws SQLException
     */
    private void rollBack() throws SQLException {
        if (connection.getAutoCommit() == false) {
            connection.rollback();
        }
    }

    /**
     * rollback before savepoint if AutoCommit == false
     * @param savepoint rollback before savepoint
     * @throws SQLException
     */
    private void rollBack(Savepoint savepoint) throws SQLException {
        if (connection.getAutoCommit() == false) {
            connection.rollback(savepoint);
        }
    }

    /**
     *
     * @param nameSavepoint name savepoint
     * @return Savepoint
     * @throws SQLException
     */
    private Savepoint setSavepoint(String nameSavepoint) throws SQLException {
        if (connection.getAutoCommit() == false) {
            return connection.setSavepoint(nameSavepoint);
        }
        return null;
    }

    /***
     *
     * @param user Object implements Users
     * @return  int quantity insert rows
     */
    private int insertTable(T user) {
        try {
            String sqlUser = factorySQL.getINSERT_USER();
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            factorySQL.setValues(ps, user);
            int execUp = ps.executeUpdate();
            ps.close();
            logger.info("Method insertTable: Execute sql = ".concat(sqlUser));
            return execUp;
        } catch (SQLException e) {
            logger.error(e);
        }
        return 0;
    }

    /**
     *
     * @param idUser long id user
     * @param idRole long id role
     * @return  int quantity insert rows
     */
    private int insertTable(long idUser, long idRole) {
        try {
            String sqlUser = factorySQL.getINSERT_USER_ROLE();
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            factorySQL.setValues(ps, idUser, idRole);
            int exec = ps.executeUpdate();
            ps.close();
            logger.info("Method insertTable: Execute sql = ".concat(sqlUser));
            return exec;
        } catch (SQLException e) {
            logger.error(e);
        }
        return 0;
    }

    /**
     *
     * @param hashMap HashMap enum role
     * @return array insert rows
     */
    private int[] insertTableRole(HashMap<Enum, String> hashMap) {
        try {
            String sqlUser = factorySQL.getINSERT_ROLE();
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            Iterator<Map.Entry<Enum, String>> iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Enum, String> entry = iterator.next();
                factorySQL.setValues(ps, entry.getKey().name(), entry.getValue());
                ps.addBatch();
            }
            int[] batch = ps.executeBatch();
            logger.info("Method insertTableRole: Execute sql = ".concat(sqlUser));
            ps.close();
            return batch;
        } catch (SQLException e) {
            logger.error(e);
        }
        return new int[0];
    }

    /**
     *
     * @param sql sql request
     * @param value String value
     * @return id
     */
    private long selectId(String sql, String value) {
        long bd = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            factorySQL.setValues(ps, value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bd = rs.getLong(1);
            }
            ps.close();
            logger.info("Method selectId: Execute sql = ".concat(sql));
        } catch (SQLException e) {
            logger.error(e);
        }
        return bd;
    }

    /**
     *
     * @param role name role
     * @return id role
     */
    private long selectIdRole(String role) {
        String sqlRole = factorySQL.getSelectRole();
        return selectId(sqlRole, role);
    }

    /***
     *
     * @param name name user
     * @return id user
     */
    private long selectIdUser(String name) {
        String sqlUser = factorySQL.getSelectByNameUser();
        return selectId(sqlUser, name);
    }

    /**
     * Close connection JDBC
     */
    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Close connection JDBC");
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean getAutoCommit() {
        try {
            return connection.getAutoCommit();
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
            logger.info("Method setAutoCommit: AutoCommit set to ".concat(String.valueOf(autoCommit)));
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    /**
     *
     * @param user Object implements Users
     * @return int quantity insert rows
     */
    @Override
    public int insert(T user) {
        int numberUpdateRowsUser = 0;
        int numberUpdateRowsRole = 0;
        long marker = 0;
        try {
            numberUpdateRowsUser = insertTable(user);
            if (numberUpdateRowsUser == 0) {
                rollBack();
                logger.info("Method insert: rollBack ".concat(user.toString()));
                return 0;
            }
            Savepoint writeUser = setSavepoint("writeUser");
            long idUser = selectIdUser(user.getName());
            long idRole = selectIdRole(user.getRole() == null ? "null":user.getRole().name());
            if ((idUser != marker) && (idRole != marker)) {
                numberUpdateRowsRole = insertTable(idUser, idRole);
                if (numberUpdateRowsRole == 0) {
                    rollBack(writeUser);
                    logger.info("Method insert: rollBack ".concat("savepoint writeUser"));
                    return 0;
                }
            }
            commit();
        } catch (SQLException e) {
            logger.error(e);
        }
        return numberUpdateRowsUser + numberUpdateRowsRole;
    }

    /**
     *
     * @param hashMap HashMap enum role
     * @return int quantity insert rows
     */
    @Override
    public int insert(HashMap<Enum, String> hashMap) {
        int[] batch = insertTableRole(hashMap);
        return batch.length;
    }

    /**
     *
     * @param arrayList ArrayList Object implements Users
     * @return int quantity insert rows
     */
    @Override
    public int insert(ArrayList<T> arrayList) {
        int quantityRows = 0;
        int size = arrayList.size();
        try {
            for (int i = 0; i < size; i++) {
                quantityRows += insert(arrayList.get(i));
            }
            commit();
        } catch (SQLException e) {
            logger.error(e);
        }
        return quantityRows;
    }

    /**
     *
     * @param name name user
     * @param factoryUsers factory users
     * @return Object implements Users
     */
    @Override
    public Users selectUser(String name, FactoryUsers factoryUsers) {
        Users user = null;
        String sqlUser = factorySQL.getSelectByNameUser();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            factorySQL.setValues(ps, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = factorySQL.getUser(rs, factoryUsers);
            }
            ps.close();
            logger.info("Method selectUser: Execute sql = ".concat(sqlUser));
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    /**
     *
     * @param login_id login_id user
     * @param name  name user
     * @param factoryUsers factory users
     * @return Object implements Users
     */
    @Override
    public Users selectUser(long login_id, String name, FactoryUsers factoryUsers) {
        Users user = null;
        String sqlUser = factorySQL.getSelectByNameLoginIdUser();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            factorySQL.setValues(ps, name, login_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = factorySQL.getUser(rs, factoryUsers);
            }
            ps.close();
            logger.info("Method selectUser: Execute sql = ".concat(sqlUser));
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    /**
     *
     * @param factoryUsers  factory users
     * @return ArrayList Object implements User
     */
    @Override
    public ArrayList<T> selectAllUser(FactoryUsers factoryUsers) {
        ArrayList<T> arrayList = new ArrayList<>();
        String sqlUser = factorySQL.getSelectAllUsers();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arrayList.add((T) factorySQL.getUser(rs, factoryUsers));
            }
            ps.close();
            logger.info("Method selectAllUser: Execute sql = ".concat(sqlUser));
        } catch (SQLException e) {
            logger.error(e);
        }
        return arrayList;
    }
}
