package part01.lesson06.task01;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Read or file source write to file destination
 * @author Oleg_Chapurin
 */
public class FromFileToFile {

    private TreeSet<String> treeSet;
    private String myDelim = "”“.,—:…;=-+*\\%^><?’‘!@#$\"~`\'/1234567890\\n\\r\\t\\br ";

    /**
     *
     * @param comparator
     *             Comparator sorting string
     */
    FromFileToFile(Comparator<String> comparator){
        treeSet = new TreeSet<>(comparator);
    }

    void readWrite() {
        ReadWriteConsole.newFlowWrite();
        ReadWriteConsole.newFlowRead();
        ReadWriteConsole.writeConsole("Specify the path to the source file with text, example: d:\\book.txt");
        String pathSource = ReadWriteConsole.readConsoleString();
        ReadWriteConsole.writeConsole("Specify the path to the destination file text, example: d:\\list.txt");
        String pathDestination = ReadWriteConsole.readConsoleString();
        /** Read or file source **/
        try (
                BufferedReader readText = new BufferedReader(new InputStreamReader(
                        new FileInputStream(pathSource), StandardCharsets.UTF_8))
        ) {
            String line;
            while ((line = readText.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, myDelim);
                while (st.hasMoreTokens()) {
                    treeSet.add(st.nextToken());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** Print console */
        treeSet.forEach(a -> ReadWriteConsole.writeConsole(a));
        ReadWriteConsole.closeAllFlow();
        /** Write to file destination */
        try (
                BufferedWriter writeText = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(pathDestination),StandardCharsets.UTF_8));
        ) {
            treeSet.forEach(a -> {
                try {
                    writeText.write(a + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FromFileToFile ftf = new FromFileToFile(new ComparatorCaseInsensitive());
        ftf.readWrite();
    }
}
