package student;

import edu.willamette.cs1.spellingbee.SpellingBeeGraphics;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellingBee {
    private static final String ENGLISH_DICTIONARY = "res/EnglishWords.txt";

    private SpellingBeeGraphics sbg;
    private char centerLetter;
    private ArrayList<String> dictionary = new ArrayList();
    private HashSet<Character> letterSet = new HashSet<>();
    private HashSet<String> foundWords = new HashSet<>();
    private HashSet<Character> wordSet = new HashSet<>();

    private int wordsFound = 0;
    private int totalScore = 0;

    public void run() {
        sbg = new SpellingBeeGraphics();
        sbg.addField("Puzzle", (s) -> puzzleAction(s));
        sbg.addButton("Solve", (s) -> solveAction());
        sbg.addField("Word", (s) -> findWords(s));
        sbg.addButton("Shuffle", (s) -> shuffleLetters());
        useDictionary();
    }

    public void useDictionary() {
        dictionary.clear();
        try {
            Scanner scanner = new Scanner(new File(ENGLISH_DICTIONARY));
            while (scanner.hasNextLine()) {

                String word = scanner.nextLine().trim().toLowerCase();
                dictionary.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void puzzleAction(String s) {
        if (!validationHelperFunc(s)) {
            return;
        }
        sbg.setBeehiveLetters(s);
    }


    private boolean validationHelperFunc(String s) {
        if (s.length() != 7) {
            sbg.showMessage("input is not 7 characters", Color.RED);
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char element = s.charAt(i);
            if (!Character.isLetter(element)) {
                sbg.showMessage("input is not part of the 26 letters", Color.RED);
                return false;
            }
        }
        letterSet.clear();
        for (int i = 0; i < s.length(); i++) {
            char characters = Character.toLowerCase(s.charAt(i));
            if (!letterSet.add(characters)) {
                sbg.showMessage("input has a letter that appears more than once", Color.RED);
                return false;
            }

        }
        centerLetter = s.charAt(0);
        return true;
    }

    private void solveAction() {
        sbg.clearWordList();
        wordsFound = 0;
        totalScore = 0;
        useDictionary();
        wordPoints();
        sbg.showMessage(+wordsFound + " words; " + totalScore + " points");

    }

    private void wordPoints() {
        for (int i = 0; i < dictionary.size(); i++) {
            String word = dictionary.get(i);
            int score = 0;
            int c = 0;
            for (char letter : sbg.getBeehiveLetters().toLowerCase().toCharArray()) {
                if (word.contains(Character.toString(letter))) {
                    c++;
                }
            }

            if (isValidWord(word)) {
                int length = word.length();
                if (c == 7) {
                    score = length + 7;
                    sbg.addWord(String.format("%s (%s)", word, score), Color.BLUE);
                } else if (length == 4) {
                    score = 1;
                    sbg.addWord(String.format("%s (%s)", word, score));
                } else {
                    score = length;
                    sbg.addWord(String.format("%s (%s)", word, score));
                }
                totalScore += score;
                wordsFound++;

            }
        }
    }


    private boolean isValidWord(String word) {
        String puzzleLetters = word.toLowerCase();
        if (word.length() < 4) {
            sbg.showMessage("word is not four or more letters long", Color.RED);
            return false;
        }
        if (!word.contains(Character.toString(centerLetter))) {
            sbg.showMessage("word does not contain the center letter", Color.RED);
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (!letterSet.contains(Character.toLowerCase(puzzleLetters.charAt(i)))) {
                sbg.showMessage("word does not contain one of given letters", Color.RED);
                return false;
            }
        }
        return true;
    }

    private void findWords(String word) {
        word = word.toLowerCase();
        if (!dictionary.contains(word)) {
            sbg.showMessage("Word is not in the dictionary", Color.RED);
        } else if (word.length() < 4) {
            sbg.showMessage("Word must be four or more letters long", Color.RED);
        } else if (!word.contains(Character.toString(centerLetter))) {
            sbg.showMessage("Word must include the center letter", Color.RED);
        } else if (!isValidWord(word)) {
            sbg.showMessage("Word include letters not in the beehive", Color.RED);
        } else if (foundWords.contains(word)) {
            sbg.showMessage("Word has already been found", Color.RED);
        } else {
            foundWords.add(word);
            int score = findScore(word);
            totalScore += score;
            sbg.addWord(String.format("%s (%s)", word, score));
            sbg.showMessage(wordsFound + " words; " + totalScore + " points");
            wordsFound++;
        }
    }

    private int findScore(String word) {
        int length = word.length();
        int score = 0;
        if (wordSet.size() == 7) {
            score = length + 7;
            sbg.addWord(String.format("%s (%s)", word, score));
        } else if (length == 4) {
            return score = 1;
        } else {
            return length;
        }
        return score = 0;
    }

    //bonus!
    public void shuffleLetters() {
        char[] lettersDisplayed = sbg.getBeehiveLetters().toLowerCase().toCharArray();
        List<Character> letters = new ArrayList<>();
        for (int i = 1; i < lettersDisplayed.length; i++) {
            letters.add(lettersDisplayed[i]);
        }
        Collections.shuffle(letters);
        StringBuilder shuffledLettersBuilder = new StringBuilder();
        shuffledLettersBuilder.append(centerLetter);
        for (char c : letters) {
            shuffledLettersBuilder.append(c);
        }
        String shuffledLetters = shuffledLettersBuilder.toString();
        sbg.setBeehiveLetters(shuffledLetters);
    }

    public static void main(String[] args) {
        new SpellingBee().run();
    }
}





