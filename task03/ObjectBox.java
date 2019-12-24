package part01.lesson03.task03;

import java.util.ArrayList;

/**
 * Stores collection Object
 *
 * @author Oleg_Chapurin
 */
class ObjectBox<T> {

    ArrayList<T> arrayObject = new ArrayList<>();

    /** Adds an element. */
    protected boolean addObject(T object){
        if(!arrayObject.contains(object)) {
            arrayObject.add(object);
            return true;
        }
        return false;
    }

    /** Delete element. */
    protected boolean deleteObject(T object){
        int index = arrayObject.indexOf(object);
        if(index >= 0) {
            arrayObject.remove(index);
            return true;
        }
        return false;
    }

    /** Print dump */
    protected void dump(){
        String message = "";
        int size = arrayObject.size();
        for (int i = 0; i < size; i++ ) {
            message += arrayObject.get(i);
        }
        System.out.println(message);
    }
}
