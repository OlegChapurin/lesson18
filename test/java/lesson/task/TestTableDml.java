package lesson.task;

import lesson.task.dao.SqlDml;
import lesson.task.dao.TableDml;
import lesson.task.dao.TableInputPostgres;
import lesson.task.pojo.Role;
import lesson.task.pojo.Users;
import lesson.task.work.FactoryUsers;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Oleg_Chapurin
 */
public class TestTableDml {

    TableDml<Users> tableDml;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Savepoint savepoint;
    @Mock
    private Logger logger;
    @Mock
    private SqlDml<Users> factorySQL;
    @Mock
    private Users user;
    @Mock
    private FactoryUsers factoryUsers;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).error(anyString());
    }

    @Test
    void testGetAutoCommitTrue() throws SQLException {
        Mockito.when(connection.getAutoCommit()).thenReturn(true);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertTrue(tableDml.getAutoCommit());
        Mockito.verify(connection, Mockito.times(1)).getAutoCommit();
    }

    @Test
    void testGetAutoCommitFalse() throws SQLException {
        Mockito.when(connection.getAutoCommit()).thenReturn(false);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertFalse(tableDml.getAutoCommit());
        Mockito.verify(connection, Mockito.times(1)).getAutoCommit();
    }

    @Test
    void testGetAutoCommitException() throws SQLException {
        Mockito.when(connection.getAutoCommit()).thenThrow(SQLException.class);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertFalse(tableDml.getAutoCommit());
        Mockito.verify(connection, Mockito.times(1)).getAutoCommit();
    }

    @Test
    void testSetAutoCommit() throws SQLException {
        doNothing().when(connection).setAutoCommit(anyBoolean());
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        tableDml.setAutoCommit(anyBoolean());
        Mockito.verify(connection, Mockito.times(1)).setAutoCommit(anyBoolean());
    }

    @Test
    void testSetAutoCommitException() throws SQLException {
        doThrow(SQLException.class).when(connection).setAutoCommit(anyBoolean());
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        tableDml.setAutoCommit(anyBoolean());
        Mockito.verify(connection, Mockito.times(1)).setAutoCommit(anyBoolean());
    }

    private void testInsert() throws SQLException {
        Mockito.when(factorySQL.getINSERT_USER()).thenReturn("");
        Mockito.when(factorySQL.getINSERT_USER_ROLE()).thenReturn("");
        Mockito.when(factorySQL.getSelectByNameUser()).thenReturn("");
        Mockito.when(factorySQL.getSelectRole()).thenReturn("");
        doNothing().when(factorySQL).setValues(preparedStatement, user);
        doNothing().when(factorySQL).setValues(preparedStatement, "");
        doNothing().when(factorySQL).setValues(preparedStatement, 1L, 1L);
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(connection.getAutoCommit()).thenReturn(false);
        Mockito.when(connection.setSavepoint(anyString())).thenReturn(savepoint);
        doNothing().when(connection).rollback();
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).close();
        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(user.getRole()).thenReturn(null);
    }

    @Test
    void testInsertUserRole() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.next()).thenReturn(true);
        testInsert();
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(2, tableDml.insert(user));

    }

    @Test
    void testInsertUserNotRole() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.next()).thenReturn(false);
        testInsert();
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(1, tableDml.insert(user));

    }

    @Test
    void testInsertArrayUserRole() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.next()).thenReturn(true);
        testInsert();
        ArrayList<Users> arrayList = new ArrayList<>();
        arrayList.add(user);
        arrayList.add(user);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(4, tableDml.insert(arrayList));
    }

    @Test
    void testInsertArrayUserNotRole() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.next()).thenReturn(false);
        testInsert();
        ArrayList<Users> arrayList = new ArrayList<>();
        arrayList.add(user);
        arrayList.add(user);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(2, tableDml.insert(arrayList));
    }


    private void testInsertRole() throws SQLException {
        Mockito.when(factorySQL.getINSERT_ROLE()).thenReturn("");
        doNothing().when(factorySQL).setValues(preparedStatement, "", "");
        doNothing().when(preparedStatement).addBatch();
        doNothing().when(preparedStatement).close();
    }

    @Test
    void testInsertRoleHashMap() throws SQLException {
        HashMap<Enum, String> hashMap = Role.getHashMapEnum();
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeBatch()).thenReturn(new int[hashMap.size()]);
        testInsertRole();
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(3, tableDml.insert(hashMap));
    }

    @Test
    void testInsertRoleEmptyHashMap() throws SQLException {
        HashMap<Enum, String> hashMap = new HashMap<>();
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeBatch()).thenReturn(new int[hashMap.size()]);
        testInsertRole();
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(0, tableDml.insert(hashMap));
    }

    @Test
    void testInsertRoleHashMapException() throws SQLException {
        HashMap<Enum, String> hashMap = Role.getHashMapEnum();
        Mockito.when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Mockito.when(preparedStatement.executeBatch()).thenReturn(new int[hashMap.size()]);
        testInsertRole();
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(0, tableDml.insert(hashMap));
    }

    private void testSelectUser() throws SQLException {
        doNothing().when(factorySQL).setValues(preparedStatement, "");
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(factorySQL.getUser(resultSet, factoryUsers)).thenReturn(user);
        doNothing().when(preparedStatement).close();
    }

    @Test
    void testSelectUserByName() throws SQLException {
        testSelectUser();
        Mockito.when(factorySQL.getSelectByNameUser()).thenReturn("");
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(factorySQL.getUser(resultSet, factoryUsers)).thenReturn(user);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertTrue(tableDml.selectUser("", factoryUsers) instanceof Users);
    }

    @Test
    void testSelectUserByNameEmpty() throws SQLException {
        testSelectUser();
        Mockito.when(factorySQL.getSelectByNameUser()).thenReturn("");
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(resultSet.next()).thenReturn(false);
        Mockito.when(factorySQL.getUser(resultSet, factoryUsers)).thenReturn(user);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertNull(tableDml.selectUser("", factoryUsers));
    }

    @Test
    void testSelectUserByNameException() throws SQLException {
        testSelectUser();
        Mockito.when(factorySQL.getSelectByNameUser()).thenReturn("");
        Mockito.when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(factorySQL.getUser(resultSet, factoryUsers)).thenReturn(user);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertNull(tableDml.selectUser("", factoryUsers));
    }


    private void testSelectAllUser() throws SQLException {
        testSelectUser();
        Mockito.when(factorySQL.getSelectAllUsers()).thenReturn("");
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Mockito.when(factorySQL.getUser(resultSet, factoryUsers)).thenReturn(user);
    }

    @Test
    void testSelectAllUserNotEmpty() throws SQLException {
        testSelectAllUser();
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(1,tableDml.selectAllUser(factoryUsers).size());
    }

    @Test
    void testSelectAllUserEmpty() throws SQLException {
        testSelectAllUser();
        Mockito.when(resultSet.next()).thenReturn(false);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(0,tableDml.selectAllUser(factoryUsers).size());
    }

    @Test
    void testSelectAllUserException() throws SQLException {
        testSelectAllUser();
        Mockito.when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        Mockito.when(resultSet.next()).thenReturn(false);
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        assertEquals(0,tableDml.selectAllUser(factoryUsers).size());
    }

    @Test
    public void testCloseConnectionNull(){
        tableDml = new TableInputPostgres<Users>(null, factorySQL, logger);
        tableDml.closeConnection();
    }

    @Test
    public void testCloseConnectionNotNull() throws SQLException {
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        tableDml.closeConnection();
    }

    @Test
    public void testCloseConnectionException() throws SQLException {
        doThrow(SQLException.class).when(connection).close();
        tableDml = new TableInputPostgres<Users>(connection, factorySQL, logger);
        tableDml.closeConnection();
    }
}
