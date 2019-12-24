package part1.lesson2.task02;

import java.util.ArrayList;

/**
 * Math operations.
 *
 * @author Oleg_Chapurin
 */
public final class Math_Operations {

    private Math_Operations(){}

    /*** Check if the number is negative */
    public static boolean numberValidity(long number) throws IllegalArgumentException{
        if (number < 0){
            throw new IllegalArgumentException("AnyNegative");
        }
        return true;
    }

    /*** Check if the number is negative */
    public static boolean numberValidity(Double number) throws IllegalArgumentException{
        if (number < 0){
            throw new IllegalArgumentException("AnyNegative");
        }
        return true;
    }

    /** Calculates the square root of each element in an array. */
    public static void squared(ArrayList<Double> arrayList){
        arrayList.replaceAll(x -> {
                    try {
                        if (numberValidity(x)) {
                            return Math.sqrt(x);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                    }
                    return x;
                }
        );
    }

    /** Print matching integers */
    public static void printMatching(ArrayList<Integer> arrayMain, ArrayList<Double> arrayCompared){
        int numberPrinted = 0;
        for (int i = 0; i < arrayCompared.size(); i++){
            Double tempNumber = arrayCompared.get(i);
            int num = (int)(tempNumber * 1);
           if (arrayMain.contains(num)){
               System.out.println(String.valueOf(num) + " --- " + String.valueOf(tempNumber));
               numberPrinted++;
           }
        }
        System.out.println(String.valueOf(numberPrinted) + " of " + String.valueOf(arrayCompared.size()));
    }
}


