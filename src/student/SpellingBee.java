package student;

import edu.willamette.cs1.spellingbee.SpellingBeeGraphics;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class SpellingBee {
    private static final String ENGLISH_DICTIONARY = "res/EnglishWords.txt";

    private SpellingBeeGraphics sbg;
    private char centerLetter;

    public void run() {
        sbg = new SpellingBeeGraphics();
        sbg.addField("Puzzle", (s) -> puzzleAction(s));
//        sbg.addButton("Solve", (s) -> solveAction());
    }

    public void useDictionary() {
        HashSet<String> dictionary = new HashSet<>();
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
            validationHelperFunc(s);
        } else {
            sbg.setBeehiveLetters(s);
        }

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


        HashSet<Character> newSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char characters = Character.toUpperCase(s.charAt(i));
            if (newSet.add(characters) == false) {
                sbg.showMessage("input has a letter that appears more than once", Color.RED);
                return false;
            }
        }
        return true;
    }



    private void solveAction() {
        sbg.clearWordList();
        for (String word : dictionary();) {
            if ()
        }
        sbg.showMessage("solveAction is not yet implemented", Color.RED);
    }

    private void isValidWord(String word){
        puzzleLetters = puzzleLetters.toLowerCase();
        if (word.length() < 4 || !word.contains(Character.toString(centerLetter))) {
            return false;
            for (char e : word.toCharArray()) {
                if (!puzzleLetters.contains(Character.toString(e))) {
                    return false;
                }
                }
            return true;

            }




    public static void main(String[] args) {
        new SpellingBee().run();
    }
}
