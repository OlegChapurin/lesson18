package part01.lesson09.task01;

import java.io.*;

/**
 * @author Oleg_Chapurin
 */
public class MyClassLoader extends ClassLoader {

    String nameClass;
    String pathFiles;

    /**
     *
     * @param nameClass name class + package
     * @param pathFiles name class downloads
     */
    public MyClassLoader(String nameClass, String pathFiles) {
        this.nameClass = nameClass;
        this.pathFiles = pathFiles.replaceAll(".java","").concat(".class");
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(nameClass.equals(name)){
            byte[] bytes;
            try {
                File file = new File(pathFiles);
                InputStream ins = new BufferedInputStream(new FileInputStream(file));
                byte[]byteFile = new byte[(int)file.length()];
                ins.read(byteFile);
                Class clazz = defineClass(name, byteFile, 0, byteFile.length);
                return clazz;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(nameClass.equals(name)){
            return findClass(name);
        }
        return super.loadClass(name);
    }
}
