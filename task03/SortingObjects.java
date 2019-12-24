package part01.lesson02.task03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Performs array sorting and measurements
 *
 * @author Oleg_Chapurin
 */
public class SortingObjects {

    private final String MESSAGE = "Enter 0 if it is unnecessary to display a sorted list otherwise 1 \n";
    private boolean trueFalse;

    /*** console input */
    private void readConsole(){
        System.out.println(MESSAGE);
        try (BufferedReader read = new BufferedReader(new InputStreamReader(System.in))) {
            String readConsole = read.readLine();
            if(readConsole.length() == 1){
                trueFalse = Integer.valueOf(readConsole) == 0 ? false : true;
            }
         } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sort(){
        readConsole();
        ReadCSV rp = new ReadCSV();
        ArrayList<String> arrayName = rp.read("resources/system.properties");
        GeneratorPerson gp = new GeneratorPerson();
        ArrayList<Person> arrayPerson = gp.getArrayPerson(arrayName);
        ArrayList<Sorting> arraySorting = new ArrayList<>();
        arraySorting.add(new SortFirst());
        arraySorting.add(new SortSecond());
        arraySorting.add(new ThirdSort());
        ArraySortingBenchmarking asb = new ArraySortingBenchmarking();
        asb.displaysListArray(this.trueFalse);
        asb.sort(arrayPerson,arraySorting);
    }

    public static void main(String[] args) {
       SortingObjects so = new SortingObjects();
       so.sort();
    }
}
