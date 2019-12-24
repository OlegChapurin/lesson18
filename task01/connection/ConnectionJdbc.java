package part01.lesson15.task01.connection;

import java.sql.Connection;

/**
 * @author Oleg_Chapurin
 */
public interface ConnectionJdbc {
    /**
     * @param url  Database URL
     * @param user Database login
     * @param password  Database password
     * @return JDBC Connection
     */
    public Connection getConnection(String url,String user, String password);
}
