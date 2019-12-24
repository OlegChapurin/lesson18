package part01.lesson03.task03;

import java.util.*;

/**
 * Processing an array of numbers
 *
 * @author Oleg_Chapurin
 */
class MathBox<T extends Number> extends ObjectBox<T>{

    private final int uid;
    private Double summa;

    {
        uid = (int)((UUID.randomUUID().getLeastSignificantBits() ^
                UUID.randomUUID().getMostSignificantBits()) >> 32);
        summa = 0.;
    }

    /** Lays out in a collection without repeating */
    private void addAllToCollection(T[] number){
        for (T num:number) {
            if(addObject((T) num)){
                summa += num.doubleValue();
            }
        }
    }

    public MathBox( T[] number) {
        addAllToCollection(number);
    }

    /** Return summa Collection */
    double summator(){
        return summa;
    }

    /** Performs a sequential division of the elements of a collection. */
    void splitter(int divider){
        int size = arrayObject.size();
        Double temp = 0.;
        Double result = 0.;
        summa = 0.;
        for (int i = 0; i < size; i++){
            temp = (Double)arrayObject.get(i);
            result = temp / divider;
            arrayObject.set(i,(T) result);
            summa += result;
        }
    }

    /** Removes an item from the collection.
     * If successful, deletion returns true a lie otherwise false
     * */
    boolean findDelete(Integer num){
        if (deleteObject((T)num)){
            summa -= num;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return uid == mathBox.hashCode() &&
                Double.compare(mathBox.summator(), summa) == 0 &&
                arrayObject.equals(mathBox.arrayObject);
    }

    @Override
    public int hashCode() {
        return uid;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "hashCode = " + uid +
                ", Summa = " + summa +
                ", numberArray = " + arrayObject +
                '}';
    }
}
