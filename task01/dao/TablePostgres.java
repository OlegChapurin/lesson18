package part01.lesson15.task01.dao;

import part01.lesson15.task01.work.FactoryUsers;
import part01.lesson15.task01.connection.ConnectionJdbc;
import part01.lesson15.task01.connection.ConnectionManagerPostgres;
import part01.lesson15.task01.pojo.Users;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 * @author Oleg_Chapurin
 */
public class TablePostgres<T extends Users> implements Table<T> {
    private ConnectionJdbc connectionJdbc =
            ConnectionManagerPostgres.getInstance();
    private Connection connection;
    private FactorySQLPostgres<T> factorySQL = new FactorySQLPostgres<>();

    /**
     * @param url  Database URL
     * @param user Database login
     * @param password  Database password
     */
    public TablePostgres(String url, String user, String password) {
        this.connection = connectionJdbc.getConnection(url, user, password);
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

    /**
     *
     * @param sql sql
     * @return int quantity rows
     */
    private int executeSQL(String sql) {
        try (
                PreparedStatement ps = connection.prepareStatement(sql);) {
            int execUp = ps.executeUpdate();
            commit();
            ps.close();
            return execUp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
            return execUp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @param idUser BigDecimal id user
     * @param idRole BigDecimal id role
     * @return  int quantity insert rows
     */
    private int insertTable(BigDecimal idUser, BigDecimal idRole) {
        try {
            String sqlUser = factorySQL.getINSERT_USER_ROLE();
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            factorySQL.setValues(ps, idUser, idRole);
            int exec = ps.executeUpdate();
            ps.close();
            return exec;
        } catch (SQLException e) {
            e.printStackTrace();
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
            ps.close();
            return batch;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    /**
     *
     * @param sql sql request
     * @param value String value
     * @return id
     */
    private BigDecimal selectId(String sql, String value) {
        BigDecimal bd = BigDecimal.valueOf(0);
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            factorySQL.setValues(ps, value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bd = rs.getBigDecimal(1);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bd;
    }

    /**
     *
     * @param role name role
     * @return id role
     */
    private BigDecimal selectIdRole(String role) {
        String sqlRole = factorySQL.getSelectRole();
        return selectId(sqlRole, role);
    }

    /***
     *
     * @param name name user
     * @return id user
     */
    private BigDecimal selectIdUser(String name) {
        String sqlUser = factorySQL.getSelectByNameUser();
        return selectId(sqlUser, name);
    }

    /**
     * check availability table
     * @param nameTable name table
     * @return boolean
     */
    private boolean isTable(String nameTable){
        boolean table = false;
        try {
            String sql = factorySQL.isTable();
            PreparedStatement ps = connection.prepareStatement(sql);
            factorySQL.setValues(ps, nameTable);
            ResultSet rs = ps.executeQuery();
            if (rs.getRow() > 0) {
                table = true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * Close connection JDBC
     */
    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean getAutoCommit() {
        try {
            return connection.getAutoCommit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param nameTable name table
     * @return boolean
     */
    @Override
    public boolean creatTable(String nameTable) {
        String sql = factorySQL.getCREATE_TABLE(nameTable);
        if (sql != null) {
            executeSQL(sql);
            return true;
        }
        return false;
    }

    /**
     *
     * @param nameTable name table
     * @return boolean
     */
    @Override
    public boolean deleteTable(String nameTable) {
        if(isTable(nameTable)) {
            String sql = factorySQL.getDELETE_TABLE(nameTable);
            if (sql != null) {
                executeSQL(sql);
                return true;
            }
        }
        return false;
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
        BigDecimal marker = BigDecimal.valueOf(0);
        try {
            numberUpdateRowsUser = insertTable(user);
            if (numberUpdateRowsUser == 0) {
                rollBack();
                return 0;
            }
            Savepoint writeUser = setSavepoint("writeUser");
            BigDecimal idUser = selectIdUser(user.getName());
            BigDecimal idRole = selectIdRole(user.getRole() == null ? "null":user.getRole().name());
            if ((idUser != marker) && (idRole != marker)) {
                numberUpdateRowsRole = insertTable(idUser, idRole);
                if (numberUpdateRowsRole == 0) {
                    rollBack(writeUser);
                    return 0;
                }
            }
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
        } catch (SQLException e) {
            e.printStackTrace();
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
    public Users selectUser(BigDecimal login_id, String name, FactoryUsers factoryUsers) {
        Users user = null;
        String sqlUser = factorySQL.getSelectByNameLoginidUser();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlUser);
            factorySQL.setValues(ps, name, login_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = factorySQL.getUser(rs, factoryUsers);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
