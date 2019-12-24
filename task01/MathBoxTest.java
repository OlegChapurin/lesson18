package part01.lesson03.task01;

/**
 * Test class MathBox
 *
 * @author Oleg_Chapurin
 */
class MathBoxTest {

    public static void main(String[] args) {
        Number[] nm1 = new Number[]{12,5,6,98};
        Number[] nm2 = new Number[]{12,5,6,100,6,77,8,9,55};
        Number[] nm = new Number[]{12,5,6,98,6,56,8,9,5};

        MathBox mb1 = new MathBox(nm1);
        MathBox mb2 = new MathBox(nm2);
        MathBox mb3 = new MathBox(nm);
        MathBox mb4 = mb3;
        System.out.println("---------- new MathBox ----------");
        System.out.println("MathBox mb1 " + mb1);
        System.out.println("MathBox mb2 " + mb2);
        System.out.println("MathBox mb3 " + mb3);
        System.out.println("MathBox mb4 " + mb4);
        System.out.println("---------- method equals ----------");
        System.out.println("mb3.equals(mb1) == " + mb3.equals(mb1));
        System.out.println("mb3.equals(mb2) == " + mb3.equals(mb2));
        System.out.println("mb3.equals(mb4) == " + mb3.equals(mb4));

        System.out.println("mb1.equals(mb2) == " + mb1.equals(mb2));
        System.out.println("mb1.equals(mb3) == " + mb1.equals(mb3));
        System.out.println("mb1.equals(mb4) == " + mb1.equals(mb4));

        System.out.println("mb2.equals(mb1) == " + mb2.equals(mb1));
        System.out.println("mb2.equals(mb3) == " + mb2.equals(mb3));
        System.out.println("mb2.equals(mb4) == " + mb2.equals(mb4));
        System.out.println("---------- method summator ----------");
        System.out.println("mb1.summator() == " + mb1.summator());
        System.out.println("mb2.summator() == " + mb2.summator());
        System.out.println("mb3.summator() == " + mb3.summator());
        System.out.println("mb4.summator() == " + mb4.summator());
        System.out.println("---------- method findDelete----------");
        System.out.println("mb1.findDelete(5) == " + mb1.findDelete(5));
        System.out.println("mb2.findDelete(77) == " + mb2.findDelete(77));
        System.out.println("mb3.findDelete(3) == " + mb3.findDelete(3));
        System.out.println("mb4.findDelete(5) == " + mb4.findDelete(5));
        System.out.println("---------- MathBox after findDelete ----------");
        System.out.println("MathBox mb1 " + mb1);
        System.out.println("MathBox mb2 " + mb2);
        System.out.println("MathBox mb3 " + mb3);
        System.out.println("MathBox mb4 " + mb4);

        mb1.splitter(2);
        mb2.splitter(3);
        mb3.splitter(3);
        mb4.splitter(2);
        System.out.println("---------- method splitter ----------");
        System.out.println("mb1.splitter(2)");
        System.out.println("mb2.splitter(3)");
        System.out.println("mb3.splitter(3)");
        System.out.println("mb4.splitter(2)");

        System.out.println("---------- MathBox after splitter ----------");
        System.out.println("MathBox mb1 " + mb1);
        System.out.println("MathBox mb2 " + mb2);
        System.out.println("MathBox mb3 " + mb3);
        System.out.println("MathBox mb4 " + mb4);
        System.out.println("---------- method equals after splitter ----------");
        System.out.println("mb3.equals(mb1) == " + mb3.equals(mb1));
        System.out.println("mb3.equals(mb2) == " + mb3.equals(mb2));
        System.out.println("mb3.equals(mb4) == " + mb3.equals(mb4));

        System.out.println("mb1.equals(mb2) == " + mb1.equals(mb2));
        System.out.println("mb1.equals(mb3) == " + mb1.equals(mb3));
        System.out.println("mb1.equals(mb4) == " + mb1.equals(mb4));

        System.out.println("mb2.equals(mb1) == " + mb2.equals(mb1));
        System.out.println("mb2.equals(mb3) == " + mb2.equals(mb3));
        System.out.println("mb2.equals(mb4) == " + mb2.equals(mb4));
        System.out.println("---------- method summator after splitter----------");
        System.out.println("mb1.summator() == " + mb1.summator());
        System.out.println("mb2.summator() == " + mb2.summator());
        System.out.println("mb3.summator() == " + mb3.summator());
        System.out.println("mb4.summator() == " + mb4.summator());
        System.out.println("---------- method findDelete after splitter----------");
        System.out.println("mb1.findDelete(2) == " + mb1.findDelete(2));
        System.out.println("mb2.findDelete(1) == " + mb2.findDelete(1));
        System.out.println("mb3.findDelete(9) == " + mb3.findDelete(9));
        System.out.println("mb4.findDelete(2) == " + mb4.findDelete(2));

//        System.out.println(m.summator());
//        m.delete(56);
//        System.out.println(m.summator());
//        m.splitter(2);
//        System.out.println(m.summator());
    }
}
