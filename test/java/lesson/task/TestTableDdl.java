package lesson.task;

import lesson.task.connection.ConnectionJdbc;
import lesson.task.dao.SqlDdl;
import lesson.task.dao.TableDdl;
import lesson.task.dao.TablePostgres;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * @author Oleg_Chapurin
 */
public class TestTableDdl {

    private TableDdl tableDdl;
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
    private SqlDdl factorySQL;

    @BeforeEach
    void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).close();
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).error(anyString());
    }

    @Test
    void testCreatTable(){
        Mockito.when(factorySQL.getCREATE_TABLE(anyString())).thenReturn("");
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        assertTrue(tableDdl.creatTable(anyString()));
    }

    @Test
    void testCreatTableNull(){
        Mockito.when(factorySQL.getCREATE_TABLE(anyString())).thenReturn(null);
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        assertFalse(tableDdl.creatTable(anyString()));
    }

    @Test
    void testCreatTableException() throws SQLException {
        Mockito.when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Mockito.when(factorySQL.getCREATE_TABLE(anyString())).thenReturn("");
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        assertFalse(tableDdl.creatTable(anyString()));
    }

    @Test
    void testDeleteTableTrue() throws SQLException {
        Mockito.when(factorySQL.getDELETE_TABLE(anyString())).thenReturn("");
        Mockito.when(factorySQL.isTable()).thenReturn("");
        doNothing().when(factorySQL).setValues(preparedStatement,"");
        Mockito.when(resultSet.getRow()).thenReturn(1);
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        assertTrue(tableDdl.deleteTable(""));
    }

    @Test
    void testDeleteTableFalse() throws SQLException {
        Mockito.when(factorySQL.getDELETE_TABLE(anyString())).thenReturn("");
        Mockito.when(factorySQL.isTable()).thenReturn("");
        doNothing().when(factorySQL).setValues(preparedStatement,"");
        Mockito.when(resultSet.getRow()).thenReturn(0);
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        assertFalse(tableDdl.deleteTable(""));
    }

    @Test
    void testDeleteTableNull() throws SQLException {
        Mockito.when(factorySQL.getDELETE_TABLE(anyString())).thenReturn(null);
        Mockito.when(factorySQL.isTable()).thenReturn("");
        doNothing().when(factorySQL).setValues(preparedStatement,"");
        Mockito.when(resultSet.getRow()).thenReturn(0);
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        assertFalse(tableDdl.deleteTable(""));
    }

    @Test
    void testDeleteTableException() throws SQLException {
        Mockito.when(factorySQL.getDELETE_TABLE(anyString())).thenReturn("");
        Mockito.when(factorySQL.isTable()).thenReturn("");
        Mockito.when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        assertFalse(tableDdl.deleteTable(""));
    }

    @Test
    public void testCloseConnectionNull(){
        tableDdl = new TablePostgres(null,factorySQL,logger);
        tableDdl.closeConnection();
    }

    @Test
    public void testCloseConnectionNotNull() throws SQLException {
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        tableDdl.closeConnection();
    }

    @Test
    public void testCloseConnectionException() throws SQLException {
        doThrow(SQLException.class).when(connection).close();
        tableDdl = new TablePostgres(connection,factorySQL,logger);
        tableDdl.closeConnection();
    }
}
