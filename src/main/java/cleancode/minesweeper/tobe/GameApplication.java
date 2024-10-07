package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.MineSweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Advanced();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        GameConfig gameConfig = new GameConfig(
                gameLevel,
                inputHandler,
                outputHandler
        );

        MineSweeper mineSweeper = new MineSweeper(gameConfig);
        mineSweeper.initialize();
        mineSweeper.run();
    }


    /*
    * DIP (Dependency Inversion Principle)
    *
    * DI (Dependency Injection) - "3"
    *
    * IoC (Inversion Of Control)
    *
    * */
}
