package kpk.dev.battleship.players;

import java.util.Map;
import java.util.Random;

import kpk.dev.battleship.grid.Cell;
import kpk.dev.battleship.grid.GridData;
import kpk.dev.battleship.ui.views.pieces.ShipData;
import kpk.dev.battleship.ui.views.pieces.builders.ShipBuilder;

/**
 * Created by krasimir.karamazov on 1/22/14.
 */
public abstract class PlayerBase {
    private GridData mGridData;
    private Map<String, ShipData> mPlayerFleet;

    public PlayerBase() {
        mPlayerFleet = ShipBuilder.buildFleet();
        mGridData = new GridData();
    }

    public final GridData getGridData() {
        return mGridData;
    }

    public final void setGridData(GridData data) {
        mGridData = data;
    }

    public abstract void startGame();

    public void autoArrageFleet() {

    }

    protected final boolean canMove(int column, int row){
        Cell cell = mGridData.getCell(column, row);
        return !cell.isSelected();
    }

    public abstract void performMove(int column ,int row);

}
