package cleancode.minesweeper.tobe.cell;

public class LandMineCell extends Cell {

    private static final String LAND_MINE_CELL = "☼";

    @Override
    public boolean isLandMine() {
        return true;
    }

    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public String getSign() {
        if (isOpened) {
            return LAND_MINE_CELL;
        }
        if (isFlagged) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }
}
