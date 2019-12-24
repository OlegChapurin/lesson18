package part01.lesson15.task01.dao;

import part01.lesson15.task01.pojo.Users;

/**
 * @author Oleg_Chapurin
 */
public interface Database<T extends Users> {
    /**
     * Close connection JDBC
     */
    void closeConnection();
    /**
     *
     * @param nameDatabase name database
     * @return boolean creat database true false
     */
    boolean creatDatabase(String nameDatabase);
}
