package part01.lesson09.task01;

import java.io.*;
import java.util.Properties;

/**
 * javac compiler
 *
 * @author Oleg_Chapurin
 */
public class MyCompiler2 {

    public void compiler(String pathFile2) {
        File file = new File(pathFile2);
        if(file.isFile()) {
            Properties p = System.getProperties();
            String sep = p.getProperty("file.separator");
            /**Get path to folder JRE */
            String jrePath = p.getProperty("java.home");
            /**Get path to javac */
            int lastIndex = jrePath.lastIndexOf(sep);
            String javac = jrePath.substring(0, lastIndex) + sep + "bin" + sep + "javac";
            if (p.getProperty("sun.desktop").equals("windows")) {
                javac += ".exe";
            } else {
                javac += ".sh";
            }
            File fileJavac = new File(javac);
            if (!fileJavac.isFile())
                try {
                    throw new FileNotFoundException(fileJavac.getAbsolutePath() + " File not found");
                } catch (
                        FileNotFoundException e) {
                    e.printStackTrace();
                }
            /** Start compile */
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(javac + " " + file.getAbsolutePath());
                BufferedReader inputStreamProcess = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = inputStreamProcess.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStreamProcess.close();
                try {
                    /** If process error */
                    if (process.waitFor() != 0){
                        System.out.println(stringBuilder.substring(0));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }
}
