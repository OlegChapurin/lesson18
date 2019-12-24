package part01.lesson08.task0102;

import java.util.List;

/**
 * Functional interface
 * @author Oleg_Chapurin
 */
public interface ArrayToList<T,E> {
    List<T> getList(E a);
}
