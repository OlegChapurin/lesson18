package lesson.task.dao;


/**
 * @author Oleg_Chapurin
 */
public interface TableDdl {

    /**
     * Close connection JDBC
     */
    void closeConnection();

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

}
