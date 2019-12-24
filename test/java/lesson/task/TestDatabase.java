package lesson.task;

import lesson.task.connection.ConnectionJdbc;
import lesson.task.dao.Database;
import lesson.task.dao.DatabasePostgres;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.postgresql.jdbc.PgArray;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Oleg_Chapurin
 */
public class TestDatabase {

    private Database databasePostgres;
    @Mock
    private ConnectionJdbc connectionJdbc;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Logger logger;
    @Mock
    private PgArray pgArray;

    @BeforeEach
    public  void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(connectionJdbc.getConnection(anyString(),anyString(),anyString())).thenReturn(connection);
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(1,"");
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(resultSet.getArray(1)).thenReturn(pgArray);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).error(anyString());
    }

    @Test
    public void testCreatDatabaseTrue() throws SQLException {
        databasePostgres = new DatabasePostgres(connection,logger);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(pgArray.toString()).thenReturn("t");
        assertFalse(databasePostgres.creatDatabase("mybd"));
        Mockito.verify(connection,Mockito.times(1)).prepareStatement(anyString());
        Mockito.verify(preparedStatement,Mockito.times(1)).executeQuery();
        Mockito.verify(preparedStatement,Mockito.times(0)).executeUpdate();
        Mockito.verify(resultSet,Mockito.times(1)).next();
        Mockito.verify(resultSet,Mockito.times(1)).getArray(1);
    }

    @Test
    public void testCreatDatabaseFalse() throws SQLException {
        databasePostgres = new DatabasePostgres(connection,logger);
        Mockito.when(resultSet.next()).thenReturn(false);
        Mockito.when(pgArray.toString()).thenReturn("f");
        assertTrue(databasePostgres.creatDatabase("mybd"));
        Mockito.verify(connection,Mockito.times(2)).prepareStatement(anyString());
        Mockito.verify(preparedStatement,Mockito.times(1)).executeQuery();
        Mockito.verify(preparedStatement,Mockito.times(1)).executeUpdate();
        Mockito.verify(resultSet,Mockito.times(1)).next();
        Mockito.verify(resultSet,Mockito.times(1)).getArray(1);
    }

    @Test
    public void testCreatDatabasePrepareStatementException() throws SQLException {
        Mockito.when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        databasePostgres = new DatabasePostgres(connection,logger);
        assertFalse(databasePostgres.creatDatabase("mybd"));
     }

    @Test
    public void testCreatDatabaseExecuteQueryException() throws SQLException {
        Mockito.when(preparedStatement.executeQuery()).thenThrow(SQLException.class);
        databasePostgres = new DatabasePostgres(connection,logger);
        assertFalse(databasePostgres.creatDatabase("mybd"));
    }

    @Test
    public void testCreatDatabaseExecuteUpdateException() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        databasePostgres = new DatabasePostgres(connection,logger);
        assertFalse(databasePostgres.creatDatabase("mybd"));
     }

     @Test
    public void testCloseConnectionNull(){
         databasePostgres = new DatabasePostgres(null,logger);
         databasePostgres.closeConnection();
     }

    @Test
    public void testCloseConnectionNotNull() throws SQLException {
        doNothing().when(connection).close();
        databasePostgres = new DatabasePostgres(connection,logger);
        databasePostgres.closeConnection();
    }

    @Test
    public void testCloseConnectionException() throws SQLException {
        doThrow(SQLException.class).when(connection).close();
        databasePostgres = new DatabasePostgres(connection,logger);
        databasePostgres.closeConnection();
       }
}
