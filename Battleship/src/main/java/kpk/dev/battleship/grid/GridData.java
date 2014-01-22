package kpk.dev.battleship.grid;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kpk.dev.battleship.ui.views.pieces.ShipData;
import kpk.dev.battleship.ui.views.pieces.builders.ShipBuilder;

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

    public static GridData generate(Map<String, ShipData> fleet) {
        GridData gridData = new GridData();

        return gridData;
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

    public boolean occupyCells(int row, int column, int numCells, ShipBuilder.Orientation orientation){
        if(orientation.equals(ShipBuilder.Orientation.HORIZONTAL)){
            if(column + numCells > NUM_COLUMNS){
                return false;
            }
        }else{
            if(row + numCells > NUM_ROWS){
                return false;
            }
        }

        for(int i = 0; i < numCells; i++) {
            Cell cellToSelect;
            if(orientation.equals(ShipBuilder.Orientation.HORIZONTAL)) {
                cellToSelect = mGridData.get(row).get(column + i);
            }else{
                cellToSelect = mGridData.get(row + i).get(column);
                if(cellToSelect != null){
                    cellToSelect.setOccupied(true);
                }
            }
        }
        return true;
    }

}
