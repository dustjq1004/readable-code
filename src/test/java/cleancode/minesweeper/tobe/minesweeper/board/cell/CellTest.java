package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @DisplayName("지뢰 셀을 생성하여 지뢰가 맞는지 확인한다.")
    @Test
    void isLandMineCell() {
        // given
        Cell landMineCell = new LandMineCell();

        // when
        boolean landMine = landMineCell.isLandMine();

        // then
        assertThat(landMine).isTrue();
    }

    @DisplayName("빈 셀을을 열고 스냅샷이 빈셀 스냅샷이 맞는지 확인한다.")
    @Test
    void isEmptySnapShotStatus() {
        // given
        Cell emptyCell = new EmptyCell();
        emptyCell.open();
        CellSnapshot snapshot = emptyCell.getSnapshot();


        // when
        CellSnapshotStatus status = snapshot.getStatus();

        // then
        assertThat(status).isEqualTo(CellSnapshotStatus.EMPTY);
    }
}
