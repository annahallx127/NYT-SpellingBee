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

    private void solveAction() {
        sbg.showMessage("solveAction is not yet implemented", Color.RED);
    }

    public static void main(String[] args) {
        new SpellingBee().run();
    }
}
