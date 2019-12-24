package part1.lesson2.task02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Creates array of random numbers ranging from 0 to 200.
 *
 * @author Oleg_Chapurin
 */
public class RandomNumbers {

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private ArrayList<Double> arrayListSquared = new ArrayList<>();

    RandomNumbers(Integer size){
        Random random = new Random();
        for(int in = 0; in < size; in++){
            Integer ranNumber = random.nextInt(200);
            arrayList.add(ranNumber);
            arrayListSquared.add(Double.valueOf(ranNumber));
        }
        Collections.sort(arrayList);
    }

    /** Return ArrayList random numbers to calculate the square root */
    public ArrayList<Double> getArrayRandomNumbersSquared() {
        return arrayListSquared;
    }

    /** Return ArrayList random numbers */
    public ArrayList<Integer> getArrayRandomNumbers() {
        return arrayList;
    }
}
