package part01.lesson05.task01;

/**
 * Defines collection methods
 *
 * @author Oleg_Chapurin
 */
public interface Box<T extends HomeAnimal> {
    /** Adds to collection */
    void add(T animal);
    /** Return collection */
    <T> T[] getCollection();
    /** Returns value by name */
    T find(String name);
    /** Returns value by uid */
    T get(int uid);

}
