package kpk.dev.battleship.ai;

import kpk.dev.battleship.grid.Cell;
import kpk.dev.battleship.utils.MarkedCell;

/**
 * Created by krasimir.karamazov on 1/21/14.
 */

public class AIMove {
    private Cell mSelectedCell;
    private boolean mIsHit;

    public Cell getSelectedCell() {
        return mSelectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        this.mSelectedCell = selectedCell;
    }

    public boolean isHit() {
        return mIsHit;
    }

    public void setIsHit(boolean madeHit) {
        this.mIsHit = madeHit;
    }
}
