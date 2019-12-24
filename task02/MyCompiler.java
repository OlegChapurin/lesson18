package part01.lesson12.task02;

import javax.tools.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Standard JIT compiler
 *
 * @author Oleg_Chapurin
 */
public class MyCompiler {

    /** List files for compiler */
    private ArrayList<String> listFiles = new ArrayList<>();

    public void setListFiles(String pathFiles) {
        this.listFiles.add(pathFiles);
    }

    public void compiler() {
        /** Get the standard JIT compiler and file manager */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(
                null, null, null);
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        Iterable<? extends JavaFileObject> compilationTask =
                fileManager.getJavaFileObjectsFromStrings(listFiles);
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationTask).call();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            System.out.println(diagnostic);
        }
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
