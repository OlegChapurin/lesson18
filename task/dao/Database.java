package lesson.task.dao;

/**
 * @author Oleg_Chapurin
 */
public interface Database {
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
