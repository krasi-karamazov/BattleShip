package kpk.dev.battleship.ui.views;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kpk.dev.battleship.gamedata.GameData;
import kpk.dev.battleship.grid.GridData;
import kpk.dev.battleship.players.AndroidPlayer;
import kpk.dev.battleship.grid.Cell;
import kpk.dev.battleship.ui.views.grid.BattleshipGrid;
import kpk.dev.battleship.ui.views.pieces.Ship;

/**
 * Created by krasimir.karamazov on 1/17/14.
 */
public class GameArea extends RelativeLayout {

    private float mOffsetX;
    private float mOffsetY;
    private BattleshipGrid mGrid;
    private Rect mGridBounds;
    private Rect mViewBounds;
    private int mCellWidth;
    private Ship mShip;
    private Point mPickupPoint;
    private int mPlayers;
    private List<BattleshipGrid> mGrids;
    public GameArea(Context context) {
        super(context);
        init();
    }

    public GameArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    public void startAddingPieces(int cellWidth) {
        mPickupPoint = new Point();


        /*mShip = new Ship(getContext());
        mShip.setNumSquares(5);
        mShip.setSquareWidth(cellWidth);
        mShip.setOrientation(Ship.Orientation.VERTICAL);
        addView(mShip);
        mShip.setOnTouchListener(getOnTouchListener());*/

        mGridBounds = new Rect(mGrid.getLeft(), mGrid.getTop(), cellWidth * 11, cellWidth * 11);
        mViewBounds = new Rect();
        mCellWidth = cellWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        /*if(changed){
            if(mGrids == null || mGrids.size() <= 0)
                return;

            double width = r / ((double)2 + .4);

            double gridWidth = width;
            for(int i = 0 ; i < GameData.getInstance().getPlayersNum(); i++) {
                RelativeLayout.LayoutParams params = (LayoutParams)mGrids.get(i).getLayoutParams();
                params.width = (int)gridWidth;
                params.height = (int)gridWidth;
                params.leftMargin = i * ((int)gridWidth + 1);
                params.addRule(RelativeLayout.CENTER_VERTICAL);
                mGrids.get(i).setLayoutParams(params);
                mGrids.get(i).requestLayout();
            }
            RelativeLayout.LayoutParams paramsLast = (RelativeLayout.LayoutParams)mGrids.get(mGrids.size() - 1).getLayoutParams();
            paramsLast.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mGrids.get(mGrids.size() - 1).setLayoutParams(paramsLast);
        }*/
    }

    private void init() {
        mGrids = new ArrayList<BattleshipGrid>();
        for(int i = 0; i < GameData.getInstance().getPlayersNum(); i++) {
            GridData data = GameData.getInstance().getPlayers().get(i).getGridData();
            BattleshipGrid grid = new BattleshipGrid(getContext(), data);
            data.addObserver(grid);
            addView(grid);
            mGrids.add(grid);
        }
    }

    private OnTouchListener getOnTouchListener() {
        return new OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                RelativeLayout.LayoutParams params = (LayoutParams)view.getLayoutParams();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPickupPoint.x = params.leftMargin;
                        mPickupPoint.y = params.topMargin;
                        mOffsetX =  motionEvent.getX();
                        mOffsetY =  motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        float posX = params.leftMargin + (motionEvent.getX() - mOffsetX);
                        float posY = params.topMargin + (motionEvent.getY() - mOffsetY);
                        if(posY >= 0 && (posY + view.getMeasuredHeight() < getBottom())){
                            params.topMargin = (int)posY;
                        }

                        params.leftMargin = (int)posX;
                        view.setLayoutParams(params);

                        if(isViewInGrid(view)) {
                            getClosestColumn(view);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Cell cell = mGrid.getCellToDrop();
                        dropPiece(cell);
                        break;
                }
                return true;
            }
        };
    }

    private void dropPiece(Cell cell) {
        boolean canOccupy =  mGrid.occupyCells(cell.getRow() - 1, cell.getColumn() - 1, mShip.getNumSquares(), mShip.getOrientation());
        RelativeLayout.LayoutParams params = (LayoutParams)mShip.getLayoutParams();
        if(canOccupy) {
            params.leftMargin = (cell.getColumn() - 1) * mCellWidth;
            params.topMargin = (cell.getRow() - 1) * mCellWidth;
        }else{
            params.leftMargin = mPickupPoint.x;
            params.topMargin = mPickupPoint.y;
        }
        mShip.setLayoutParams(params);
    }

    private void getClosestColumn(View view) {
        int l = view.getLeft();
        int t = view.getTop();
        if(l == 0) {
            l = 1;
        }
        if(t == 0){
            t = 1;
        }
        double column =(double)l / ((double)mGridBounds.width()  -  mCellWidth);
        double row =(double)t / ((double)mGridBounds.height());
        double horizPos = round(column, 1, BigDecimal.ROUND_HALF_UP);
        double vertPos = round(row, 1, BigDecimal.ROUND_HALF_UP);
        mGrid.repaintGrid((int)(horizPos * 10), (int)(vertPos * 10), ((Ship)view).getOrientation(), ((Ship)view).getNumSquares());
    }

    public double round(double unrounded, int precision, int roundingMode) {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }

    private boolean isViewInGrid(View view) {
        mViewBounds.left = view.getLeft();
        mViewBounds.right = view.getRight();
        mViewBounds.top = view.getTop();
        mViewBounds.bottom = view.getBottom();
        if(Rect.intersects(mViewBounds, mGridBounds)){
            return true;
        }
        return false;
    }
}
