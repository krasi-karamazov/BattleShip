package kpk.dev.battleship.ui.views.grid;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import kpk.dev.battleship.grid.Cell;
import kpk.dev.battleship.grid.GridData;
import kpk.dev.battleship.grid.GridObserver;
import kpk.dev.battleship.ui.views.pieces.Ship;
import kpk.dev.battleship.ui.views.pieces.builders.ShipBuilder;
import kpk.dev.battleship.utils.MarkedCell;

/**
 * Created by krasimir.karamazov on 1/16/14.
 */
public class BattleshipGrid extends RelativeLayout implements GridObserver
{

    private final List<LinkedList<Square>> mItems = new LinkedList<LinkedList<Square>>();
    private int mCellWidth;
    private List<MarkedCell> mSelection;
    private GridData mData;
    private Handler mHandler;

    public BattleshipGrid(Context context, GridData data) {
        super(context);
        initGrid(data);
    }

    public void initGrid(GridData data) {
        mData = data;
        for(int i = 0; i < GridData.NUM_COLUMNS ; i++) {
            LinkedList<Square> row = new LinkedList<Square>();
            for(int j = 0; j < GridData.NUM_ROWS; j++){
                Square sq = new Square(getContext());
                sq.setLayoutParams(new LayoutParams(mCellWidth, mCellWidth));
                row.add(sq);
                addView(row.get(j));
            }
            mItems.add(row);
        }
        data.addObserver(this);
        mSelection = new LinkedList<MarkedCell>();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public int getCellWidth() {
        return mCellWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        //int biggerSide = (getHeight() > getWidth())?getHeight():getWidth();
        //mCellWidth = ((biggerSide / 2)/ GridData.NUM_COLUMNS);
        mCellWidth = getWidth() / GridData.NUM_COLUMNS;
        int row = 1;
        for(int i = 1; i <= this.getChildCount(); i++) {
            int col = (i + GridData.NUM_COLUMNS) % GridData.NUM_COLUMNS;
            int left = ((col == 0)?GridData.NUM_COLUMNS - 1:col - 1) * mCellWidth;
            int right = left + mCellWidth;
            int top = (row - 1) * mCellWidth;
            int bottom = top + mCellWidth;
            Square sq = (Square)getChildAt(i - 1);
            sq.setHorizPos(((col == 0)?GridData.NUM_COLUMNS:col));
            sq.setVertPos(row);
            LayoutParams params = (LayoutParams)sq.getLayoutParams();
            params.width = mCellWidth;
            params.height = mCellWidth;
            sq.setLayoutParams(params);
            sq.layout(left, top, right, bottom);
            if(col == 0){
                row++;
            }
        }
    }

    public void repaintGrid(int column, int row, ShipBuilder.Orientation orientation, int numCells) {
        if(row >= mItems.size() || column >= mItems.size()) {
            return;
        }
        setSelection(false);

        for(int i = 0; i < numCells; i++) {
            MarkedCell cell = new MarkedCell();
            if(orientation.equals(ShipBuilder.Orientation.HORIZONTAL)){
                cell.setColumn(column + i);
                cell.setRow(row);
            }else{
                cell.setColumn(column);
                cell.setRow(row + i);
            }
            mSelection.add(cell);
        }

        setSelection(true);
    }

    public Cell getCellToDrop() {
        return mData.getCell(mSelection.get(0).getColumn(), mSelection.get(0).getRow());
    }

    public boolean occupyCells(int row, int column, int numCells, ShipBuilder.Orientation orientation) {
        setSelection(false);
        return mData.occupyCells(row, column, numCells, orientation);
    }

    private void setSelection(boolean select) {
        for(MarkedCell cell : mSelection) {
            if(cell.getRow() >= mItems.size() || cell.getColumn() >= mItems.size()){
                continue;
            }
            Square sq = mItems.get(cell.getRow()).get(cell.getColumn());
            sq.setIsSelected(select);
            sq.invalidate();
        }
        if(!select)
            mSelection.clear();
    }

    @Override
    public void gridChanged(final int column, final int row) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Square sq = mItems.get(row).get(column);
                sq.setIsSelected(true);
                sq.invalidate();
            }
        });
    }
}
