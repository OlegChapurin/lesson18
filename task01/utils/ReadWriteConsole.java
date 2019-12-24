package part01.lesson10.task01.utils;

import java.io.*;

/**
 * @author Oleg_Chapurin
 */
public final class ReadWriteConsole {

    static BufferedReader br = null;
    static BufferedWriter bw = null;
    /** Reads a line of text. */
    private static String readConsole(){
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    /** Opens Flow Read */
    public static boolean newFlowRead(){
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
        }catch (IllegalArgumentException e){
            if(br != null){
                try{
                    br.close();
                }catch (IOException b){
                    b.printStackTrace();
                }
            }
            return false;
        }
        return true;
    }
    /** Opens Flow Write */
    public static boolean newFlowWrite(){
        try {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }catch (IllegalArgumentException e){
            if(bw != null){
                try{
                    bw.close();
                }catch (IOException b){
                    b.printStackTrace();
                }
            }
            return false;
        }
        return true;
    }
    /** Reads numbers from the console */
    public static Integer readConsoleNumber(){
        Integer number = 0;
        boolean attempt = true;
        while(attempt) {
            try {
                number = Integer.valueOf(readConsole());
                attempt = false;
            } catch (NumberFormatException e) {
                writeConsole("Incorrect format data, try again");
            }
        }
        return number;
    }
    /** Reads a line of text from the console */
    public static String readConsoleString(){
        return readConsole().toLowerCase().trim();
    }
    /** Prints text to the console */
    public static void writeConsole(String message){
        try {
            bw.write(message + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Close all Flow */
    public static void closeAllFlow(){
        if(br != null){
            try{
                br.close();
            }catch (IOException b){
                b.printStackTrace();
            }
        }
        if(bw != null){
            try{
                bw.close();
            }catch (IOException b){
                b.printStackTrace();
            }
        }
    }
}
