package kpk.dev.battleship.grid;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import kpk.dev.battleship.ui.views.pieces.ShipData;
import kpk.dev.battleship.ui.views.pieces.builders.ShipBuilder;

/**
 * Created by krasimir.karamazov on 1/21/14.
 */
public class GridData {
    public static final int NUM_COLUMNS = 11;
    public static final int NUM_ROWS = 11;
    final public List<LinkedList<Cell>> mGridData = new LinkedList<LinkedList<Cell>>();
    private GridObserver mObserver;
    private ReentrantLock lock;
    private Random mRand;
    public GridData() {
        initGrid();
        mRand = new Random(System.currentTimeMillis() + (long)Math.random());
    }


    public void addObserver(GridObserver observer) {
        mObserver = observer;
    }

    public void notifyObservers(int row, int column) {
        mObserver.gridChanged(row, column);
    }

    public static GridData generate(Map<String, ShipData> fleet) {
        GridData gridData = new GridData();
        Iterator<Map.Entry<String, ShipData>> iterator = fleet.entrySet().iterator();
        Log.d("Battleship", "ORIENTATION 45");
        while(iterator.hasNext()) {
            Map.Entry<String, ShipData> entry = iterator.next();
            Log.d("Battleship", "ORIENTATION" + entry.getValue().getOrientation());
        }
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

    public boolean occupyCell(int column, int row) {
        Cell cell = mGridData.get(row).get(column);
        if(cell.isOccupied()) {
            return false;
        }else {
            cell.setOccupied(true);
            return true;
        }
    }

    public boolean selectCell(int column, int row) {

        Cell cell = mGridData.get(row).get(column);
        if(cell.isSelected()) {
            return false;
        }else {
            cell.setSelected(true);
            notifyObservers(column, row);
            return true;
        }
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

        for(int i = column; i < NUM_COLUMNS; i++) {
            Cell cellToSelect;
            if(orientation.equals(ShipBuilder.Orientation.HORIZONTAL)) {
                cellToSelect = mGridData.get(row).get(column + i);
            }else{
                cellToSelect = mGridData.get(row + i).get(column);
            }
            if(cellToSelect.isOccupied()) {
                mGridData.clear();
                occupyCells(mRand.nextInt(NUM_COLUMNS), mRand.nextInt(NUM_COLUMNS), numCells, orientation);
                break;
            }else{
                cellToSelect.setOccupied(true);
            }
        }
        return true;
    }

}
