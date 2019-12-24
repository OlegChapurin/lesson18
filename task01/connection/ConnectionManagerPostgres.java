package part01.lesson15.task01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public class ConnectionManagerPostgres implements ConnectionJdbc {

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
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        return connection;
    }
}
