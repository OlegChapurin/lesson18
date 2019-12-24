package part01.lesson07.task01;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates an array of random numbers
 *
 * @author Oleg_Chapurin
 */
class RandomArrayInteger {

    private  ArrayList<Integer> arrayList = new ArrayList<>();

    /** Get an array of random numbers */
    protected ArrayList<Integer> getRandomArray(int size){
        arrayList.clear();
        Random random = new Random();
        for (int i = 0; i < size; i++){
            arrayList.add(random.nextInt(size));
        }
        return arrayList;
    }
}
