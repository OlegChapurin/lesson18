package part01.lesson15.task01.dao;

import part01.lesson15.task01.work.FactoryUsers;
import part01.lesson15.task01.pojo.Users;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Oleg_Chapurin
 */
public interface Table<T extends Users> {
    /**
     * Close connection JDBC
     */
    void closeConnection();
    boolean getAutoCommit();
    void setAutoCommit(boolean autoCommit);
    /**
     *
     * @param nameTable name table
     * @return boolean
     */
    boolean creatTable(String nameTable);
    /**
     *
     * @param nameTable name table
     * @return boolean
     */
    boolean deleteTable(String nameTable);
    /**
     *
     * @param user Object implements Users
     * @return int quantity insert rows
     */
    int insert(T user);
    /**
     *
     * @param hashMap HashMap enum role
     * @return int quantity insert rows
     */
    int insert(HashMap<Enum,String> hashMap);
    /**
     *
     * @param arrayList ArrayList Object implements Users
     * @return int quantity insert rows
     */
    int insert(ArrayList<T> arrayList);
    /**
     *
     * @param name name user
     * @param factoryUsers factory users
     * @return Object implements Users
     */
    Users selectUser(String name, FactoryUsers factoryUsers);
    /**
     *
     * @param login_id login_id user
     * @param name  name user
     * @param factoryUsers factory users
     * @return Object implements Users
     */
    Users selectUser(BigDecimal login_id,String name, FactoryUsers factoryUsers);
    /**
     *
     * @param factoryUsers  factory users
     * @return ArrayList Object implements User
     */
    ArrayList<T> selectAllUser(FactoryUsers factoryUsers);
}
