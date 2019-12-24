package lesson.task;

import lesson.task.dao.SqlDml;
import lesson.task.dao.SqlDmlPostgres;
import lesson.task.pojo.Role;
import lesson.task.pojo.Users;
import lesson.task.work.FactoryUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;

/**
 * @author Oleg_Chapurin
 */
public class TestSqlDml {
    private SqlDml sqlDml;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Users user;
    @Mock
    private FactoryUsers factoryUsers;


    @BeforeEach
    void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        doNothing().when(preparedStatement).setLong(anyInt(), anyLong());
    }

    @Test
    void testSetValues() throws SQLException {
        sqlDml = new SqlDmlPostgres();
        sqlDml.setValues(preparedStatement, user);
        sqlDml.setValues(preparedStatement, "");
        sqlDml.setValues(preparedStatement, 1L, 1L);
        sqlDml.setValues(preparedStatement, "", 1L);
        sqlDml.setValues(preparedStatement, "", "");
        Mockito.verify(preparedStatement, Mockito.times(9)).setString(anyInt(), anyString());
        Mockito.verify(preparedStatement, Mockito.times(4)).setLong(anyInt(), anyLong());
    }

    @Test
    void testGetUser() throws SQLException {
        Mockito.when(factoryUsers.newInstance(anyString())).thenReturn(user);
        Mockito.when(resultSet.getString(anyString())).thenReturn(anyString());
        Mockito.when(resultSet.getString("role")).thenReturn(String.valueOf(Role.BILLING));
        Mockito.when(resultSet.getLong(anyString())).thenReturn(anyLong());
        sqlDml = new SqlDmlPostgres<Users>();
        assertTrue(sqlDml.getUser(resultSet,factoryUsers) instanceof Users);
    }
}
