package kpk.dev.battleship.grid;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import kpk.dev.battleship.ui.views.grid.Square;
import kpk.dev.battleship.ui.views.pieces.Ship;

/**
 * Created by krasimir.karamazov on 1/21/14.
 */
public class GridData {
    public static final int NUM_COLUMNS = 11;
    public static final int NUM_ROWS = 11;
    final public List<LinkedList<Cell>> mGridData = new LinkedList<LinkedList<Cell>>();

    public GridData() {
        initGrid();
    }

    private void initGrid() {
        for(int i = 0; i < NUM_COLUMNS ; i++) {
            LinkedList<Cell> row = new LinkedList<Cell>();
            for(int j = 0; j < NUM_ROWS; j++){
                Cell cell = new Cell();
                cell.setRow(i + 1);
                cell.setColumn(j + 1);
                row.add(cell);
            }
            mGridData.add(row);
        }
    }

    public void occupyCell(int column, int row) {
        mGridData.get(row).get(column).setOccupied(true);
    }

    public void selectCell(int column, int row) {
        mGridData.get(row).get(column).setSelected(true);
    }

    public Cell getCell(int column, int row) {
        return mGridData.get(row).get(column);
    }

    public void occupyCells(int row, int column, int numCells, Ship.Orientation orientation) {
        for(int i = 0; i < numCells; i++) {
            Cell cellToSelect;
            if(orientation.equals(Ship.Orientation.HORIZONTAL)) {
                cellToSelect = mGridData.get(row).get(column + i);
            }else{
                cellToSelect = mGridData.get(row + i).get(column);
                if(cellToSelect != null){
                    cellToSelect.setOccupied(true);
                }
            }
        }
    }

}
