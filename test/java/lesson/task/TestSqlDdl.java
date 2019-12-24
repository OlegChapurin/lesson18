package lesson.task;

import lesson.task.dao.SqlDdl;
import lesson.task.dao.SqlDdlPostgres;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.mockito.Mockito.doNothing;

/**
 * @author Oleg_Chapurin
 */
public class TestSqlDdl {

    private SqlDdl sqlDdl;
    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TestSetValues() throws SQLException {
        doNothing().when(preparedStatement).setString(1,"");
        sqlDdl = new SqlDdlPostgres();
        sqlDdl.setValues(preparedStatement,"");
        Mockito.verify(preparedStatement, Mockito.times(1)).
                setString(1,"");
    }

}
