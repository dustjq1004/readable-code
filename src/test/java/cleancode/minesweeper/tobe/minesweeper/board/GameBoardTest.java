package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.VeryBeginner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @DisplayName("VeryBeginner 레벨에 맞는 row, col, landmineCount 가 생성 됐는지 확인한다.")
    @Test
    void equalToVeryBeginner() {
        // given
        VeryBeginner veryBeginner = new VeryBeginner();
        GameBoard gameBoard = new GameBoard(veryBeginner);
        gameBoard.initializeGame();
        int rowSize = gameBoard.getRowSize();
        int colSize = gameBoard.getColSize();

        // when
        int landMineCount = 0;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                CellPosition cellPosition = CellPosition.of(row, col);
                gameBoard.openAt(cellPosition);
                CellSnapshot snapshot = gameBoard.getSnapshot(cellPosition);
                if (snapshot.isSameStatus(CellSnapshotStatus.LAND_MINE)) {
                    landMineCount++;
                }
            }
        }

        // then
        assertThat(rowSize).isEqualTo(veryBeginner.getRowSize());
        assertThat(colSize).isEqualTo(veryBeginner.getColSize());
        assertThat(landMineCount).isEqualTo(veryBeginner.getLandMineCount());
    }

    @DisplayName("게임 보드 생성 후 유효하지 않은 셀 위치를 확인한다.")
    @Test
    void isInvalidCellPosition() {
        // given
        GameBoard gameBoard = new GameBoard(new Beginner());

        // when && then
        assertThatThrownBy(() -> gameBoard.isInvalidCellPosition(CellPosition.of(-1, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

}
