package part01.lesson11.task01;

import java.io.*;
import java.util.TreeMap;

/**
 * Serialize Deserialize object to of JSON.
 *
 * @author Oleg_Chapurin
 */
public class SerializeJson {

    private SerializationToFile jsonFile = new JsonFile();
    private SerializationToObject jsonObject = new JsonObject();

    /** Write file */
    private void write(String text, String file) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(file))
        ) {
            writer.write(text);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Reade file */
    private TreeMap<Integer, String> Reade(String file) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))
        ) {
            String line;
            Integer index = 0;
            while ((line = reader.readLine()) != null) {
                treeMap.put(index, line.trim());
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return treeMap;
    }

    /** Serialize object to JSON */
    protected void serialize(Object object, String file) {
        String textJson = jsonFile.serialize(object);
        write(textJson, file);
    }

    /** Deserialize file json to object */
   protected Object deSerialized(String file) {
        TreeMap<Integer,String> treeMap = Reade(file);
        return jsonObject.deSerialize(treeMap);
    }
}