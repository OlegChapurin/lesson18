package part01.lesson11.task01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Convert simple arrays into a collection object.
 *
 * @author Oleg_Chapurin
 */
class ConversionArrayToList {

    private ArrayToList<Number,byte[]> arrayByteToList =
            (b) -> IntStream.range(0, b.length)
                    .mapToObj(index -> b[index])
                    .collect(Collectors.toList());

    private ArrayToList<Number,int[]> arrayIntToList =
            (i) -> IntStream.range(0, i.length)
                    .mapToObj(index -> i[index])
                    .collect(Collectors.toList());

    private ArrayToList<Number,double[]> arrayDoubleToList =
            (d) -> IntStream.range(0, d.length)
                    .mapToObj(index -> d[index])
                    .collect(Collectors.toList());

    private ArrayToList<Number,float[]> arrayFloatToList =
            (f) -> IntStream.range(0, f.length)
                    .mapToObj(index -> f[index])
                    .collect(Collectors.toList());

    private ArrayToList<Number,long[]> arrayLongToList =
            (l) -> IntStream.range(0, l.length)
                    .mapToObj(index -> l[index])
                    .collect(Collectors.toList());

    private ArrayToList<Number,short[]> arrayShortToList =
            (s) -> IntStream.range(0, s.length)
                    .mapToObj(index -> s[index])
                    .collect(Collectors.toList());

    private ArrayToList<Character,char[]> arrayCharToList =
            (s) -> IntStream.range(0, s.length)
                    .mapToObj(index -> s[index])
                    .collect(Collectors.toList());

    private ArrayToList<String,String[]> arrayStringToList =
            (str) -> Arrays.stream(str).collect(Collectors.toList());

    private ArrayToList<Boolean,boolean[]> arrayBooleanToList =
            (s) -> IntStream.range(0, s.length)
                    .mapToObj(index -> s[index])
                    .collect(Collectors.toList());

    private ArrayToList<Object,Object[]> arrayObjectToList =
            (s) -> IntStream.range(0, s.length)
                    .mapToObj(index -> s[index])
                    .collect(Collectors.toList());

    protected List<Number> getListOfArray(int[] array){
        return arrayIntToList.getList(array);
    }

    protected List<Number> getListOfArray(byte[] array){
        return arrayByteToList.getList(array);
    }

    protected List<Number> getListOfArray(double[] array){
        return arrayDoubleToList.getList(array);
    }

    protected List<Number> getListOfArray(float[] array){
        return arrayFloatToList.getList(array);
    }

    protected List<Number> getListOfArray(long[] array){
        return arrayLongToList.getList(array);
    }

    protected List<Number> getListOfArray(short[] array){
        return arrayShortToList.getList(array);
    }

    protected List<Character> getListOfArray(char[] array){
        return arrayCharToList.getList(array);
    }

    protected List<String> getListOfArray(String[] array){
        return arrayStringToList.getList(array);
    }

    protected List<Boolean> getListOfArray(boolean[] array){
        return arrayBooleanToList.getList(array);
    }

    protected List<Object> getListOfArray(Object[] array){
        return arrayObjectToList.getList(array);
    }
}
