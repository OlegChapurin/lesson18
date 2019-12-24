package part01.lesson09.task01;

import java.io.*;

/**
 * @author Oleg_Chapurin
 */
final class ReadWrite {

    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static BufferedReader brf = null;
    static BufferedWriter bwf = null;

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
    static boolean newFlowReadConsole(){
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
    static boolean newFlowWriteConsole(){
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
    static Integer readConsoleNumber(){
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
    static String readConsoleString(){
        return readConsole().trim();
    }

    /** Prints text to the console */
    static void writeConsole(String message){
        try {
            bw.write(message + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Close all Flow */
    static void closeAllFlowConsole(){
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

    /** Opens Flow Read */
    static boolean newFlowReadFile(String pathFile){
        try {
            brf = new BufferedReader(new FileReader(pathFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    /** Opens Flow Write */
    static boolean newFlowWriteFile(String pathFile){
        try {
            bwf = new BufferedWriter(new FileWriter(pathFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /** Reads a line of file */
    private static String readFile(){
        try {
            return brf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /** Reads a line of file */
    static String readLineFile(){
        String line  = readFile();
        if(line  != null){
            return line.trim();
        }
        return null;
    }

    /** Write a line in file */
    static void writeFile(String message){
        try {
            bwf.write(message + "\n");
            bwf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Close write Flow */
    static void closeWriterFlowFile(){
       if(bwf != null){
            try{
                bwf.close();
            }catch (IOException b){
                b.printStackTrace();
            }
        }
    }

    /** Close read Flow */
    static void closeReaderFlowFile(){
        if(brf != null){
            try{
                brf.close();
            }catch (IOException b){
                b.printStackTrace();
            }
        }
    }
}
