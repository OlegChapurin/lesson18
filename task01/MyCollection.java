package part01.lesson05.task01;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Oleg_Chapurin
 */
public class MyCollection<T extends HomeAnimal> implements Box<T> {
    /** Search by uid */
    private TreeMap<Integer, T> animal = new TreeMap<>();
    /** Search by nickname */
    private TreeMap<String, T> nameKey = new TreeMap<>();
    /** Adds to collection */
    @Override
    public void add(T homeAnimal) throws AddValueException{
        if(!animal.containsValue(homeAnimal)){
            animal.put(homeAnimal.getUID(),homeAnimal);
            nameKey.put(homeAnimal.getNickName(),homeAnimal);
        }else{
            throw new AddValueException(homeAnimal.toString());
        }
    }
    /** Return collection */
    @Override
    public <T> T[] getCollection() {
        ArrayList<T> homeAnimal = new ArrayList<>(animal.size());
        return (T[]) animal.values().toArray(homeAnimal.toArray());
    }
    /** Returns value by name */
    @Override
    public T find(String name) {
        T valueAnimal = nameKey.get(name);
        if(valueAnimal == null ){
            throw new FindValueException("Nickname not found");
        }
        return valueAnimal;
    }
    /** Returns value by uid */
    @Override
    public T get(int uid) throws FindValueException {
        T valueAnimal = animal.get(uid);
        if(valueAnimal == null ){
            throw new FindValueException("ID not found");
        }
        return valueAnimal;
    }

    @Override
    public String toString() {
        return "MyCollection{" +
                "animal=" + animal +
                '}';
    }
}
