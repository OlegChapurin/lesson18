package part01.lesson15.task01.work;

import part01.lesson15.task01.dao.Table;
import part01.lesson15.task01.dao.TablePostgres;
import part01.lesson15.task01.pojo.Role;
import part01.lesson15.task01.pojo.Users;

/**
 * delete create tables
 * @author Oleg_Chapurin
 */
public class NewTables {

    /**
     * delete create tables
     */
    public void creatTables(){
        Table<Users> table = new TablePostgres<>(
                "jdbc:postgresql://localhost:5432/mybd",
                "postgres",
                "postgres");
        System.out.println("Delete table user " + table.deleteTable("USER"));
        System.out.println("Delete table role " + table.deleteTable("ROLE"));
        System.out.println("Delete table user_role " + table.deleteTable("USER_ROLE"));
        System.out.println("Creat table user " + table.creatTable("USER"));
        System.out.println("Creat table role " + table.creatTable("ROLE"));
        System.out.println("Creat table user_role " + table.creatTable("USER_ROLE"));
        System.out.println("The role table is full " + table.insert(Role.getHashMapEnum()));
        table.closeConnection();
    }

    public static void main(String[] args) {
        NewTables nt = new NewTables();
        nt.creatTables();

    }
}
