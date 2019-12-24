package part01.lesson06.task02;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * File generator
 *
 * @author Oleg_Chapurin
 */
public class FileGenerator<T extends TextGenerator> {
    private T generator;

    FileGenerator(T generator) {
        this.generator = generator;
    }

    /**
     * Creates the required number of files
     *
     * @param path        file location directory
     * @param numberFiles number of files
     * @param sizeFiles   size file in char
     * @param words       array of words
     * @param probability likely occurrences of words from array of words
     */
    public void getFiles(String path, int numberFiles, int sizeFiles, String[] words, int probability) {
        generator.setSizeText(sizeFiles);
        generator.setProbability(probability);
        generator.fillArrayWords(new ArrayList<String>(Arrays.asList(words)), 15);
        for (int i = 0; i < numberFiles; i++) {
            try (
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path + i + ".txt"))
            ) {
                writer.write(generator.getText(15, 15, 20));
                writer.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
