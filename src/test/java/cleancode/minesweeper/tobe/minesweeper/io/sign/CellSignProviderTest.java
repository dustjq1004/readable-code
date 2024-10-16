package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellSignProviderTest {

    @DisplayName("아직 확인 전인 셀의 CellSign을 찾는다.")
    @Test
    void test() {
        // given
        CellSnapshot cellSnapshot = CellSnapshot.ofUnchecked();

        // when
        String cellSign = CellSignProvider.findCellSignFrom(cellSnapshot);

        // then
        assertThat(cellSign).isEqualTo("□");
    }

}
