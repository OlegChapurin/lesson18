package part01.lesson09.task01;

/**
 * @author Oleg_Chapurin
 */
public class MyClassLoader2 extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
