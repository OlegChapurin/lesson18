package part01.lesson05.task01;

import java.util.ArrayList;

/**
 * Prepares an array with data for initial filling.
 *
 * @author Oleg_Chapurin
 */
class DataTest {
    ArrayList<HomeAnimal> fillData() {
        ArrayList<HomeAnimal> arrayAnimal = new ArrayList<>();
        Person p1 = new Person(25, Gender.MAN, "Bert");
        Person p2 = new Person(20, Gender.WOMAN, "Aala");
        Person p3 = new Person(25, Gender.MAN, "Dwain");
        Person p4 = new Person(45, Gender.WOMAN, "Brea");
        Person p5 = new Person(18, Gender.MAN, "Girard");
        Person p6 = new Person(35, Gender.WOMAN, "Clareta");
        Person p7 = new Person(23, Gender.MAN, "Hume");
        Person p8 = new Person(45, Gender.WOMAN, "Danae");
        Person p9 = new Person(25, Gender.MAN, "Pan");
        Person p10 = new Person(35, Gender.WOMAN, "Isobel");
        Person p11 = new Person(25, Gender.MAN, "Morenike");
        Person p12 = new Person(45, Gender.WOMAN, "Keara");
        HomeAnimal homeAnimal1 = new HomeAnimal("sss", 5, p1);
        HomeAnimal homeAnimal2 = new HomeAnimal("aaa", 8, p2);
        HomeAnimal homeAnimal3 = new HomeAnimal("bbb", 10, p3);
        HomeAnimal homeAnimal4 = new HomeAnimal("nnn", 12, p4);
        HomeAnimal homeAnimal5 = new HomeAnimal("sss", 5, p5);
        HomeAnimal homeAnimal6 = new HomeAnimal("aaa", 8, p6);
        HomeAnimal homeAnimal7 = new HomeAnimal("bbb", 10, p7);
        HomeAnimal homeAnimal8 = new HomeAnimal("nnn", 12, p8);
        HomeAnimal homeAnimal9 = new HomeAnimal("sss", 5, p9);
        HomeAnimal homeAnimal10 = new HomeAnimal("aaa", 8, p10);
        HomeAnimal homeAnimal11 = new HomeAnimal("bbb", 10, p11);
        HomeAnimal homeAnimal12 = new HomeAnimal("nnn", 12, p12);
        HomeAnimal homeAnimal13 = new HomeAnimal("aaaa", 7, p1);
        HomeAnimal homeAnimal14 = new HomeAnimal("cccc", 7, p1);
        arrayAnimal.add(homeAnimal1);
        arrayAnimal.add(homeAnimal2);
        arrayAnimal.add(homeAnimal3);
        arrayAnimal.add(homeAnimal4);
        arrayAnimal.add(homeAnimal5);
        arrayAnimal.add(homeAnimal6);
        arrayAnimal.add(homeAnimal7);
        arrayAnimal.add(homeAnimal8);
        arrayAnimal.add(homeAnimal9);
        arrayAnimal.add(homeAnimal10);
        arrayAnimal.add(homeAnimal11);
        arrayAnimal.add(homeAnimal12);
        arrayAnimal.add(homeAnimal13);
        arrayAnimal.add(homeAnimal14);
        return arrayAnimal;
    }
}
