package part01.lesson06.task02;

import java.util.ArrayList;

/**
 * @author Oleg_Chapurin
 */
public interface TextGenerator {
    /** Sets the size of the text. */
    void setSizeText(int size);
    /** Sets probability.*/
    void setProbability(int prob);
    /** Fills an array with data from an input array */
    void fillArrayWords(ArrayList<String> arrayList, int maxLengthWord);
    /*** Get formed text */
    String getText(int maxLengthWord, int randomLengthOffer, int randomLengthParagraph);
}
