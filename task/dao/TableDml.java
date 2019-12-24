package lesson.task.dao;

import lesson.task.pojo.Users;
import lesson.task.work.FactoryUsers;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Oleg_Chapurin
 */
public interface TableDml<T extends Users> {
    /**
     * Close connection JDBC
     */
    void closeConnection();

    /**
     *
     * @return AutoCommit included yes, no
     */
    boolean getAutoCommit();

    /**
     *
     * @param autoCommit true, false
     */
    void setAutoCommit(boolean autoCommit);

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
    Users selectUser(long login_id, String name, FactoryUsers factoryUsers);
    /**
     *
     * @param factoryUsers  factory users
     * @return ArrayList Object implements User
     */
    ArrayList<T> selectAllUser(FactoryUsers factoryUsers);
}
