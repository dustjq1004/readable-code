package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellSignFinderTest {

    @DisplayName("NumberCellSign Snapshot에 맞는 CellSign을 찾는다.")
    @Test
    void findNumberCellSignProvider() {
        // given
        int nearbyLandMineCount = 3;
        CellSnapshot cellSnapshot = CellSnapshot.of(CellSnapshotStatus.NUMBER, nearbyLandMineCount);
        CellSignFinder cellSignFinder = new CellSignFinder();

        // when
        String cellSign = cellSignFinder.findCellSignFrom(cellSnapshot);

        // then
        assertThat(cellSign).isEqualTo(String.valueOf(nearbyLandMineCount));
    }


}
