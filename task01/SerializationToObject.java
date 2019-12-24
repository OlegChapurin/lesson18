package part01.lesson11.task01;

import java.util.TreeMap;

/**
 * @author Oleg_Chapurin.
 */
public interface SerializationToObject {
    Object deSerialize(TreeMap<Integer, String> treeMap);
}
