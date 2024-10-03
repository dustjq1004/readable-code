package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.gamelevel.Middle;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Advanced();
        MineSweeper mineSweeper = new MineSweeper(gameLevel);
        mineSweeper.run();
    }
}
