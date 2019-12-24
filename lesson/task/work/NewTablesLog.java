package lesson.task.work;

import lesson.task.dao.TableDdl;
import lesson.task.dao.TablePostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Oleg_Chapurin
 */
public class NewTablesLog {
    private static final Logger logger = LogManager.getRootLogger();

    /**
     * delete create tables
     */
    public void creatTables(){
        TableDdl table = new TablePostgres(
                "jdbc:postgresql://localhost:5432/log",
                "postgres",
                "postgres");
        logger.info("Method creatTables: Delete table logs " + table.deleteTable("LOGS"));
        logger.info("Method creatTables: Creat table logs " + table.creatTable("LOGS"));
        table.closeConnection();
    }

    public static void main(String[] args) {
        NewTablesLog nt = new NewTablesLog();
        nt.creatTables();

    }
}
