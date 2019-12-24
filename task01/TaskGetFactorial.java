package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.Map;

/**
 * Calculates the factorial of a number and
 * puts it in the general collection Map<key,value>
 *
 * @author Oleg_Chapurin
 */
class TaskGetFactorial implements Runnable {
    private Integer number;
    private BigInteger factorial;
    private Map<Integer, BigInteger> map;

    /**
     *
     * @param hashMap general collection
     * @param factorial The number for which factorial is calculated
     */
    TaskGetFactorial(Map<Integer, BigInteger> hashMap, Integer factorial) {
        map = hashMap;
        this.factorial = BigInteger.valueOf(factorial);
        number = factorial;
    }

    /** Factorial is calculated **/
    private void getFactorial() {
        if (null == map.get(number)) {
            if (0 == number) {
                map.putIfAbsent(number, BigInteger.ONE);
            } else {
                BigInteger f2 = BigInteger.ONE;
                for (int i = (number - 1); i > 1; --i) {
                    if (null != (f2 = map.get(i))) {
                        factorial = factorial.multiply(f2);
                        break;
                    }
                    factorial = factorial.multiply(BigInteger.valueOf(i));
                }
                map.putIfAbsent(number, factorial);
            }
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        getFactorial();
    }
}
