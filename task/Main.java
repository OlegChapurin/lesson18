package lesson.task;

import lesson.task.work.*;

/**
 * @author Oleg_Chapurin
 */
public class Main {
    public static void main(String[] args) {
        NewDatabaseLog ndl = new NewDatabaseLog();
        ndl.creatDatabase();
        NewTablesLog ntl = new NewTablesLog();
        ntl.creatTables();
        NewDatabase nd = new NewDatabase();
        nd.creatDatabase();
        NewTables nt = new NewTables();
        nt.creatTables();
        DataInput di = new DataInput();
        di.fill();
    }
}
