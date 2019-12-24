package part01.lesson03.task02;

import java.util.UUID;

/**
 * Test class ObjectBox
 *
 * @author Oleg_Chapurin
 */
public class ObjectBoxTest {
    public static void main(String[] args) {
        ObjectBox ob = new ObjectBox();
        ob.addObject(25);
        ob.addObject("<-->");
        ob.addObject(UUID.randomUUID());
        ob.addObject("<-->");
        ob.addObject(45);
        ob.dump();
        ob.deleteObject(25);
        ob.dump();
    }
}
