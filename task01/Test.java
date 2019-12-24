package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

/**
 * Tests factorial calculation for each element in an array
 * in a given number of threads and takes it to the console
 *
 * @author Oleg_Chapurin
 */
public class Test {

    public static void main(String[] args) {
        ReadWriteConsole.newFlowRead();
        ReadWriteConsole.newFlowWrite();
        ReadWriteConsole.writeConsole("Specify the size of the array with randomly generated numbers");
        int sizeArray = ReadWriteConsole.readConsoleNumber();
        ReadWriteConsole.writeConsole("Specify Pool Size");
        int sizePool = ReadWriteConsole.readConsoleNumber();
        RandomArrayInteger randomArrayInteger = new RandomArrayInteger();
        ArrayList<Integer> arrayList = randomArrayInteger.getRandomArray(sizeArray);
        Factorial factorial = new Factorial();
        Map<Integer, BigInteger> mapFactorial =factorial.CalculateFactorial(arrayList,sizePool);
        mapFactorial.forEach((key, value) -> {
            ReadWriteConsole.writeConsole(key + "! = " + value);
        });
        ReadWriteConsole.closeAllFlow();
    }
}
