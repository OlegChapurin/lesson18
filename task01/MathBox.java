package part01.lesson03.task01;

import java.util.*;

/**
 * Processing an array of numbers
 *
 * @author Oleg_Chapurin
 */
class MathBox{

    private final int uid;
    private double summa;
    private ArrayList<Number> numberArray = new ArrayList<>();

    {
        uid = (int)((UUID.randomUUID().getLeastSignificantBits() ^
                UUID.randomUUID().getMostSignificantBits()) >> 32);
    }

    /** Lays out in a collection without repeating */
    private void addAllToCollection(Number[] number){
        for (Number num:number) {
            if(!numberArray.contains(num)){
                numberArray.add(num);
                summa += num.doubleValue();
            }
        }
    }

    MathBox(Number[] number) {
        addAllToCollection(number);
    }

    /** Return summa Collection */
    double summator(){
        return summa;
    }

    /** Performs a sequential division of the elements of a collection. */
    void splitter(int divider){
        int size = numberArray.size();
        double temp = 0.0;
        double result = 0.0;
        summa = 0.;
        for (int i = 0; i < size; i++){
            temp = numberArray.get(i).doubleValue();
            result = temp / divider;
            numberArray.set(i,result);
            summa += result;
        }
    }

    /** Removes an item from the collection.
     * If successful, deletion returns true a lie otherwise false
     * */
    boolean findDelete(Integer num){
        int index = numberArray.indexOf(num);
        if(index >= 0) {
            summa -= numberArray.remove(index).doubleValue();
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
                numberArray.equals(mathBox.numberArray);
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
                ", numberArray = " + numberArray +
                '}';
    }
}
