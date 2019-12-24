package part01.lesson15.task01;

import part01.lesson15.task01.work.DataInput;
import part01.lesson15.task01.work.NewDatabase;
import part01.lesson15.task01.work.NewTables;

/**
 * @author Oleg_Chapurin
 */
public class Main {
    public static void main(String[] args) {
        NewDatabase nd = new NewDatabase();
        nd.creatDatabase();
        NewTables nt = new NewTables();
        nt.creatTables();
        DataInput di = new DataInput();
        di.fill();
    }
}
