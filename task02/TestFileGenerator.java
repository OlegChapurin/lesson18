package part01.lesson06.task02;

/**
 * Test
 *
 * @author Oleg_Chapurin
 */
public class TestFileGenerator {

    public static void main(String[] args) {
        FileGenerator test =  new FileGenerator(new Text());
        String data[] = {"---1---","---2---","---3---","---4---","---5---"};
        test.getFiles("d:\\",5,10000,data,3);
        System.out.println("File Generation Successful");
    }


}
