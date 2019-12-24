package part01.lesson12.task02;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:MaxMetaspaceSize=512m
 * return OutOfMemoryError Metaspace
 *
 * @author Oleg_Chapurin
 */
public class MetaspaceOutOfMemoryError {

    /**
     * -XX:MaxMetaspaceSize=128m
     * return OutOfMemoryError Metaspace
     */

    public static void main(String[] args) {
        List<Class> list = new ArrayList<>();
        /** Compiler */
        MyCompiler myCompiler = new MyCompiler();
        myCompiler.setListFiles("src\\main\\java\\part01\\lesson12\\task02\\ReadWriteConsole.java");
        myCompiler.compiler();
        String fileSource = "src\\main\\java\\part01\\lesson12\\task02\\ReadWriteConsole.class";
        for (int i = 0; i < 100000000; i++){
            ClassLoader l = new MyClassLoader("part01.lesson12.task02.ReadWriteConsole",fileSource);
            try {
                Class<?> someClass = l.loadClass("part01.lesson12.task02.ReadWriteConsole");
                list.add(someClass);
                if ((i % 5) == 0) {
                    if (list.size() > 2) {
                        list.remove(1);
                    }
                }
                    Thread.sleep(1);
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
