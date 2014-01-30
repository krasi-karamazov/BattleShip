package kpk.dev.battleship.grid;

import android.graphics.Rect;

/**
 * Created by krasimir.karamazov on 1/30/14.
 */
public class Occupiable {
    private boolean mCanOccupy;
    private Rect mRect;

    public void setCanOccupy(boolean canOccupy) {
        mCanOccupy = canOccupy;
    }
}
