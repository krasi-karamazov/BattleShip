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
    private static Random mRand = new Random(System.currentTimeMillis() + (long)Math.random());;
    public GridData() {
        initGrid();

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

        while(iterator.hasNext()) {
            Map.Entry<String, ShipData> entry = iterator.next();
            while(!gridData.occupyCells(mRand.nextInt(NUM_COLUMNS), mRand.nextInt(NUM_COLUMNS), entry.getValue().getNumSquares(), entry.getValue().getOrientation())) {
                gridData.occupyCells(mRand.nextInt(NUM_COLUMNS), mRand.nextInt(NUM_COLUMNS), entry.getValue().getNumSquares(), entry.getValue().getOrientation());
            }

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

        if(canOccupy(row, column, numCells, orientation)) {

        }else{
            return false;
        }
        /*Log.d("Battleship", "start: " + (start));
        Log.d("Battleship", "end: " + (end));
        Log.d("Battleship", "total: " + (start + numCells));*/

        return true;
    }

    private boolean canOccupy(int row, int column, int numCells, ShipBuilder.Orientation orientation){
        int start = 0;
        int end = 0;
        if(orientation.equals(ShipBuilder.Orientation.HORIZONTAL)){
            start = column;
            end = start + numCells;
            if(end >= NUM_COLUMNS){
                return false;
            }

        }else{
            start = row;
            end = start + numCells;
            if(end >= NUM_ROWS){
                return false;
            }
        }

        for(int i = start; i < start + numCells; i++) {
            Cell cellToSelect;
            if(orientation.equals(ShipBuilder.Orientation.HORIZONTAL)) {
                cellToSelect = mGridData.get(row).get(i);
            }else{
                cellToSelect = mGridData.get(i).get(column);
            }
            if(cellToSelect.isOccupied()) {
                return false;
            }
        }
        return true;
    }

}
