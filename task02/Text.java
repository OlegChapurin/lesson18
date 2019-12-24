package part01.lesson06.task02;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates a file and fills in a random way
 *
 * @author Oleg_Chapurin
 */
public class Text implements TextGenerator {
    /**
     * Letter string
     */
    private String listLetters = "abcdefghijklmnopqrstuvwxyz";
    /**
     * Character list
     */
    private String punctuation = ".!?";
    /**
     * Array word
     */
    private ArrayList<String> arrayWords = new ArrayList<>();
    /**
     * Random number generator
     */
    private Random random = new Random();
    /**
     * Text for file
     */
    private StringBuffer text = new StringBuffer();
    /**
     * Word for array randomly generated
     */
    private StringBuffer word = new StringBuffer();
    /**
     * Size file
     */
    private int size;
    /**
     * probability %
     */
    private double probability;
    /**
     * Array select latch
     */
    private boolean ofArray = true;

    /**
     * Checks text size in characters
     */
    private boolean startGenerate() {
        return size > text.length() ? true : false;
    }

    /**
     * Add char
     */
    private boolean addChar(char symbol) {
        if (size > text.length()) {
            text.append(symbol);
            return true;
        }
        return false;
    }

    /**
     * Deletes the character at the end
     */
    private boolean deleteLastChar() {
        if (text.length() > 0) {
            text.deleteCharAt(text.length() - 1);
            return true;
        }
        return false;
    }

    /**
     * Generate a word to populate an array
     */
    private String generateWord(int maxLengthWord) {
        word.delete(0, word.length());
        int randomLength = random.nextInt(maxLengthWord - 1) + 1;
        for (int i = 0; i < randomLength; i++) {
            word.append(listLetters.charAt(
                    random.nextInt(listLetters.length())));
        }
        return word.substring(0);
    }

    /**
     * Generate word
     */
    private void generateWord(int maxLengthWord, boolean newStart) {
        int index;
        char symbol;
        int randomLength = random.nextInt(maxLengthWord - 1) + 1;
        for (int i = 0; i < randomLength; i++) {
            index = random.nextInt(listLetters.length());
            symbol = listLetters.charAt(index);
            /** Form a capital letter */
            if (newStart) {
                symbol = Character.toUpperCase(symbol);
                newStart = false;
            }
            if (!addChar(symbol)) {
                /** Stop the cycle when reaching the specified size */
                break;
            }
        }
    }

    /**
     * Fill the array
     */
    private void getWordOfArray(int maxLengthWord) {
        double randomLength = random.nextDouble();
        if ((randomLength <= probability) &
                ((size - text.length()) >= maxLengthWord)) {
            int index = random.nextInt(arrayWords.size() - 1);
            text.append(arrayWords.get(index).toCharArray());
            ofArray = false;
        } else {
            generateWord(maxLengthWord, false);
        }
    }

    /**
     * Generate offer
     */
    private void generateOffer(int maxLengthWord, int randomLengthOffer) {
        int randomLength = random.nextInt(randomLengthOffer - 1) + 1;
        int end = randomLength - 1;
        for (int i = 0; i < randomLength; i++) {
            if ((i != 0) & ofArray & (arrayWords.size() > 0)) {
                getWordOfArray(maxLengthWord);
            } else {
                generateWord(maxLengthWord, i == 0 ? true : false);
            }
            if (startGenerate()) {
                if (i < end) {
                    if (random.nextInt(5) == 1) {
                        if (!addChar(",".charAt(0))) {
                            break;
                        }
                    }
                    if (!addChar(" ".charAt(0))) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if (addChar(punctuation.charAt(random.nextInt(2) + 1))) {
            addChar(" ".charAt(0));
        }
        ofArray = true;
    }

    /**
     * Generate paragraph
     */
    private void generateParagraph(int maxLengthWord, int randomLengthOffer, int randomLengthParagraph) {
        int randomLength = random.nextInt(randomLengthParagraph - 1) + 1;
        for (int i = 0; i < randomLength; i++) {
            if (startGenerate()) {
                generateOffer(maxLengthWord, randomLengthOffer);
            } else {
                break;
            }
        }
        addChar("\n".charAt(0));
        addChar("\r".charAt(0));
    }

    /**
     * Fills an array with randomly generated words
     **/
    private void fillArrayWords(int maxSize, int maxLengthWord) {
        int size = random.nextInt(maxSize);
        for (int i = 0; i < size; i++) {
            arrayWords.add(generateWord(maxLengthWord));
        }
    }

    /*** Get formed text */
    @Override
    public String getText(int maxLengthWord, int randomLengthOffer, int randomLengthParagraph) {
        while (startGenerate()) {
            generateParagraph(maxLengthWord, randomLengthOffer, randomLengthParagraph);
        }
        deleteLastChar();
        addChar(punctuation.charAt(random.nextInt(2) + 1));
        return text.substring(0);
    }

    /**
     * Fills an array with data from an input array
     */
    @Override
    public void fillArrayWords(ArrayList<String> arrayList, int maxLengthWord) {
        if (arrayList.size() > 1) {
            arrayWords.addAll(arrayList);
        } else {
            fillArrayWords(1000, maxLengthWord);
        }
    }

    /**
     * Sets the size of the text.
     */
    @Override
    public void setSizeText(int size) {
        this.size = size;
    }

    /**
     * Sets probability.
     */
    @Override
    public void setProbability(int prob) {
        this.probability = 1.0 / prob;
    }
}
