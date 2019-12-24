package part1.lesson2.task02;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Generates 'N' random numbers. For each 'N', the quadratic root of
 * 'Q' is calculated. If the square of the integer part
 * 'Q' is 'N', print display.
 *
 * @author Oleg_Chapurin
  */
public class RandomNumberGenerator {
    private String message ="Enter any number type int, not negative. \n" +
            "Enter 0 exit";
    /** Print message*/
    public void toReport(){
        System.out.println(message);
    }

    /** Read console */
    public int readNumberConsole() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        while(true) {
            rng.toReport();
            try {
                int numberConsole = rng.readNumberConsole();
                if (Math_Operations.numberValidity(numberConsole)) {
                    if (numberConsole == 0) {
                        break;
                    }
                }
                RandomNumbers rn = new RandomNumbers(numberConsole);
                Math_Operations.squared(rn.getArrayRandomNumbersSquared());
                Math_Operations.printMatching(rn.getArrayRandomNumbers(), rn.getArrayRandomNumbersSquared());
                break;
            }catch(InputMismatchException | IllegalArgumentException e){
                System.out.println(e);
            }
        }
    }
}
