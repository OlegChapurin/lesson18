package lesson.task.work;

import lesson.task.dao.Database;
import lesson.task.dao.DatabasePostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Oleg_Chapurin
 */
public class NewDatabaseLog {
    private static final Logger logger = LogManager.getRootLogger();

    /**
     * create new database
     */
    public void creatDatabase(){
        Database db = new DatabasePostgres(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
        boolean newDatabase = db.creatDatabase("log");
        db.closeConnection();
        logger.info("Method creatDatabase: New database " + newDatabase);
    }

    public static void main(String[] args) {
        NewDatabaseLog nd = new NewDatabaseLog();
        nd.creatDatabase();
    }
}
