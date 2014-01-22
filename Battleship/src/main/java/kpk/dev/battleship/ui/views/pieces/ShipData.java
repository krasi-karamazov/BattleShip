package kpk.dev.battleship.ui.views.pieces;

import kpk.dev.battleship.ui.views.pieces.builders.ShipBuilder;

/**
 * Created by krasimir.karamazov on 1/22/14.
 */
public class ShipData {

    private int mNumSquares;
    private String mName;
    private ShipBuilder.Orientation mOrientation;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getNumSquares() {
        return mNumSquares;
    }

    public void setNumSquares(int mNumSquares) {
        this.mNumSquares = mNumSquares;
    }

    public ShipBuilder.Orientation getOrientation() {
        return mOrientation;
    }

    public void setOrientation(ShipBuilder.Orientation mOrientation) {
        this.mOrientation = mOrientation;
    }
}
