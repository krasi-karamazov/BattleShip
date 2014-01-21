package kpk.dev.battleship.grid;

/**
 * Created by krasimir.karamazov on 1/21/14.
 */
public class Cell {

    private int mColumn;
    private int mRow;
    private boolean mOccupied;
    private boolean mSelected;

    public boolean isOccupied() {
        return mOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.mOccupied = occupied;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    public int getColumn() {
        return mColumn;
    }

    public void setColumn(int column) {
        this.mColumn = column;
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        this.mRow = row;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(getClass().getSimpleName());
        builder.append("Column:" + mColumn);
        builder.append(" Row:" + mRow);
        builder.append(" Occupied:" + mOccupied);
        builder.append(" Selected:" + mSelected);
        return builder.toString();
    }
}
