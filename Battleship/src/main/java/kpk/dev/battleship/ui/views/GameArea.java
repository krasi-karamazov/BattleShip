package kpk.dev.battleship.ui.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.math.BigDecimal;

import kpk.dev.battleship.R;
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
    private int[] mCellGridPositions;
    private int mCellWidth;
    public GameArea(Context context) {
        super(context);
    }

    public GameArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {

    }

    public void startAddingPieces(int cellWidth) {
        mGrid = (BattleshipGrid)findViewById(R.id.battleship_grid);
        Ship ship = new Ship(getContext());
        ship.setNumSquares(5);
        ship.setSquareWidth(cellWidth);
        ship.setOrientation(Ship.Orientation.VERTICAL);
        addView(ship);
        ship.setOnTouchListener(getOnTouchListener());
        mGridBounds = new Rect(mGrid.getLeft(), mGrid.getTop(), cellWidth * 11, cellWidth * 11);
        mViewBounds = new Rect();
        mCellWidth = cellWidth;

    }

    private OnTouchListener getOnTouchListener() {
        return new OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                RelativeLayout.LayoutParams params = (LayoutParams)view.getLayoutParams();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
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
                }
                return true;
            }
        };
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
