package part01.lesson02.task01;

import java.util.Scanner;

/**
 * @author Oleg_Chapurin
 */
public class HelloWorld {

    private String hw = "Help \n enter 1 return NullPointerException \n" +
            " enter 2 return ArrayIndexOutOfBoundsException \n" +
            " enter 3 return RuntimeException \n" +
            " enter 0 exit";

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.imitationException();
    }

    /** return NullPointerException
     * ArrayIndexOutOfBoundsException
     * RuntimeException */
    public void imitationException() {
        while (true) {
            String inputNumber = inputFromConsole();
            if (inputNumber.equals("1")) {
                npe();
            }
            if (inputNumber.equals("2")) {
                arrayIndexOutOfBounds();
            }
            if (inputNumber.equals("3")) {
                yourOptionException();
            }
            if (inputNumber.equals("0")) {
                break;
            }
        }
    }

    /** input from console */
    private String inputFromConsole(){
        Scanner inCon = new Scanner(System.in);
        printHelloWorld();
        return inCon.next();
    }

    /** return NullPointerException */
    private void npe(){
        HelloWorld helloWorld = null;
        helloWorld.printHelloWorld();
    }

    /** return ArrayIndexOutOfBoundsException */
    private void arrayIndexOutOfBounds(){
        int ar[] = new int[5];
        System.out.println(ar[6]);
    }

    /** return RuntimeException */
    private void yourOptionException(){
        throw new RuntimeException();
    }

    /** print help */
    private void printHelloWorld(){
        System.out.println(hw);
    }
}
