package part01.lesson08.task0102;

import part01.lesson08.task0102.test.TestAA;

/**
 * Conducts a serialization test JSON.
 *
 * @author Oleg_Chapurin
 */
public class TestSerialization {

    public static void main(String[] args) {
        TestAA testObject = new TestAA();
        SerializeJson serializeJson = new SerializeJson();
        /** We serialize the object to JSON */
        serializeJson.serialize(testObject,"d:\\223.txt");
        /** Deserialize the object from JSON */
        Object object = serializeJson.deSerialized("d:\\223.txt");
    }
}
