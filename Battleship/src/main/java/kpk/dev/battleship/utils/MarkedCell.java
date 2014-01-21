package kpk.dev.battleship.utils;

/**
 * Created by krasimir.karamazov on 1/21/14.
 */
public class MarkedCell{

    private int mRow;
    private int mColumn;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(MarkedCell.class.getSimpleName());
        builder.append(" row " + mRow);
        builder.append(" column " + mColumn);
        return builder.toString();
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        this.mRow = row;
    }

    public int getColumn() {
        return mColumn;
    }

    public void setColumn(int column) {
        this.mColumn = column;
    }
}
