package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Calculates the factorial of each element in an array in a
 * given number of threads
 *
 * @author Oleg_Chapurin
 */
public class Factorial {

    private Map<Integer, BigInteger> collection = new ConcurrentHashMap<>();

    /**
     *
     * @param array Array of elements Integer
     * @param sizePool size pool
     * @return  Map key Integer value BigInteger
     */
    public Map<Integer, BigInteger> CalculateFactorial(ArrayList<Integer> array, int sizePool){
        ExecutorService pool = Executors.newFixedThreadPool(sizePool);
        int sizeArray = array.size();
        for(int i = 0; i < sizeArray; i++){
            pool.execute(new TaskGetFactorial(collection,array.get(i)));
        }
        pool.shutdown();
        return collection;
    }
}
