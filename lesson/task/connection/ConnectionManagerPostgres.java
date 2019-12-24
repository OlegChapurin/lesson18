package lesson.task.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public class ConnectionManagerPostgres implements ConnectionJdbc {

    private static final Logger logger = LogManager.getLogger(ConnectionManagerPostgres.class);
    private static ConnectionJdbc connectionJdbc;

    private ConnectionManagerPostgres(){

    }

    /**
     *
     * @return ConnectionJdbc
     */
    public static ConnectionJdbc getInstance(){
        if(connectionJdbc == null){
            connectionJdbc = new ConnectionManagerPostgres();
            logger.info("Method ConnectionJdbc: new ConnectionManagerPostgres");
        }
        return connectionJdbc;
    }

    /**
     * @param url  Database URL
     * @param user Database login
     * @param password  Database password
     * @return a connection to the URL
     */
    @Override
    public Connection getConnection(String url,String user, String password) {
        Connection connection = null;
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url,user,password);
                logger.info("Method getConnection: Get connection");
            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e);
            }
        return connection;
    }
}
