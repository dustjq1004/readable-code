package cleancode.minesweeper.tobe.cell;

public interface Cell {

    // Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰 여부
    // Cell의 상태 : 깃발 유무, 열렸다/닫혔다. 사용자가 확인

    boolean hasLandMineCount();

    CellSnapshot getSnapshot();

    boolean isLandMine();

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();
}
