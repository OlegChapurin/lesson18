package part01.lesson03.task02;

import java.util.ArrayList;

/**
 * Stores collection Object
 *
 * @author Oleg_Chapurin
 */
class ObjectBox {

    private ArrayList<Object> arrayObject = new ArrayList<>();

    /** Adds an element. */
    void addObject(Object object){
        arrayObject.add(object);
    }

    /** Delete element. */
    boolean deleteObject(Object object){
        int index = arrayObject.indexOf(object);
        if(index >= 0) {
            arrayObject.remove(index);
            return true;
        }
        return false;
    }

    /** Print dump */
    void dump(){
        String message = "";
        int size = arrayObject.size();
        for (int i = 0; i < size; i++ ) {
            message += arrayObject.get(i);
        }
        System.out.println(message);
    }
}
