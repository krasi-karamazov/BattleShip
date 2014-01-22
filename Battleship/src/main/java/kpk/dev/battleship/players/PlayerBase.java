package kpk.dev.battleship.players;

import java.util.Map;

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

    public GridData getGridData() {
        return mGridData;
    }

    public abstract void startGame();

    public void autoArrageFleet() {
        //TODO autoarrange
    }

    public abstract void performMove();

}
