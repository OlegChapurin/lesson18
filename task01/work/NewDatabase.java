package part01.lesson15.task01.work;

import part01.lesson15.task01.dao.Database;
import part01.lesson15.task01.dao.DatabasePostgres;

/**
 * create new database
 * @author Oleg_Chapurin
 */
public class NewDatabase {

    /**
     * create new database
     */
    public void creatDatabase(){
        Database db = new DatabasePostgres(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");
        boolean newDatabase = db.creatDatabase("mybd");
        db.closeConnection();
        System.out.println("New database " + newDatabase);
    }

    public static void main(String[] args) {
        NewDatabase nd = new NewDatabase();
        nd.creatDatabase();
    }
}
