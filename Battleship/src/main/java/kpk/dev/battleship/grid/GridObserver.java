package kpk.dev.battleship.grid;

/**
 * Created by krasimir.karamazov on 1/29/14.
 */
public interface GridObserver {
    public void gridChanged(int column, int row);
}
