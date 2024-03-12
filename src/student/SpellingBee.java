package student;

import edu.willamette.cs1.spellingbee.SpellingBeeGraphics;
import java.awt.Color;

public class SpellingBee {
    private static final String ENGLISH_DICTIONARY = "res/EnglishWords.txt";

    private SpellingBeeGraphics sbg;

    public void run() {
        sbg = new SpellingBeeGraphics();
        sbg.addField("Puzzle", (s) -> puzzleAction(s));
        sbg.addButton("Solve", (s) -> solveAction());
    }

    private void puzzleAction(String s) {
        sbg.showMessage("puzzleAction is not yet implemented", Color.RED);
    }

    private boolean validationHelperFunc(String s) {
        if (s.length() != 7) {
            sbg.showMessage("input is not 7 characters", Color.RED);
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char element = s.charAt(i);
            if (!Character.isLetter(element)) {
                sbg.showMessage("input is not part of the 26 letters");
                return false;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            
        }
    }

    private void solveAction() {
        sbg.showMessage("solveAction is not yet implemented", Color.RED);
    }

    public static void main(String[] args) {
        new SpellingBee().run();
    }
}
