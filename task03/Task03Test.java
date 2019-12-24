package part01.lesson03.task03;

/**
 * Test class MathBox
 *
 * @author Oleg_Chapurin
 */
public class Task03Test {
    public static void main(String[] args) {
        Number[] nm1 = new Number[]{12, 5, 6, 98};
        MathBox<Number> mb1 = new MathBox(nm1);
        mb1.dump();
        Integer[] nm2 = new Integer[]{12, 5, 6, 98};
        MathBox<Integer> mb2 = new MathBox(nm2);
        mb2.dump();
    }
}
