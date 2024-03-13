package student;

import edu.willamette.cs1.spellingbee.SpellingBeeGraphics;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class SpellingBee {
    private static final String ENGLISH_DICTIONARY = "res/EnglishWords.txt";

    private SpellingBeeGraphics sbg;
    private char centerLetter;
    private ArrayList<String> dictionary = new ArrayList();
    private HashSet<Character> letterSet = new HashSet<>();

    public void run() {
        sbg = new SpellingBeeGraphics();
        sbg.addField("Puzzle", (s) -> puzzleAction(s));
        sbg.addButton("Solve", (s) -> solveAction());
    }

    public void useDictionary() {
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

        for (int i = 0; i < s.length(); i++) {
            char characters = Character.toUpperCase(s.charAt(i));
            if (letterSet.add(characters) == false) {
                sbg.showMessage("input has a letter that appears more than once", Color.RED);
                return false;
            }

        }
        centerLetter = s.charAt(0);
        return true;
    }

    private void solveAction() {
        useDictionary();


        for (int i = 0; i < dictionary.size(); i++) {
            for (Character letter: dictionary.get(i).toCharArray()){
                if (!letterSet.contains(letter)) {
                        sbg.showMessage("does not contain any of the inputted letters", Color.RED);
                }
            }
                if (isValidWord(dictionary.get(i))) {
                    sbg.addWord(dictionary.get(i));
                }
            }

        sbg.showMessage("");
        System.out.println("new");
        System.out.println("new");


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
        HashSet<Character> wordSet = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            if (!letterSet.contains(Character.toUpperCase(puzzleLetters.charAt(i)))) {
                sbg.showMessage("word does not contain one of given letters", Color.RED);
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        new SpellingBee().run();
    }
}




