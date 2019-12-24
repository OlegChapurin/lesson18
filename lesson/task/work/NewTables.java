package lesson.task.work;

import lesson.task.dao.TableDdl;
import lesson.task.dao.TablePostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * delete create tables
 * @author Oleg_Chapurin
 */
public class NewTables {

    private static final Logger logger = LogManager.getLogger();

    /**
     * delete create tables
     */
    public void creatTables(){
        TableDdl table = new TablePostgres(
                "jdbc:postgresql://localhost:5432/mybd",
                "postgres",
                "postgres");
        logger.info("Method creatTables Delete table user " + table.deleteTable("USER"));
        logger.info("Method creatTables Delete table role " + table.deleteTable("ROLE"));
        logger.info("Method creatTables Delete table user_role " + table.deleteTable("USER_ROLE"));
        logger.info("Method creatTables Creat table user " + table.creatTable("USER"));
        logger.info("Method creatTables Creat table role " + table.creatTable("ROLE"));
        logger.info("Method creatTables Creat table user_role " + table.creatTable("USER_ROLE"));
        table.closeConnection();
    }

    public static void main(String[] args) {
        NewTables nt = new NewTables();
        nt.creatTables();

    }
}
