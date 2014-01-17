package kpk.dev.battleship.ui.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by krasimir.karamazov on 1/16/14.
 */
public class BattleshipGrid extends RelativeLayout {
    private static final int NUM_COLUMNS = 11;
    private static final int NUM_ROWS = 11;
    private List<Square> mItems;
    private int mCellWidth;

    public BattleshipGrid(Context context) {
        super(context);
        init();
    }

    public BattleshipGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BattleshipGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mItems = new LinkedList<Square>();
        for(int i = 0; i < NUM_COLUMNS * NUM_ROWS; i++) {
            mItems.add(new Square(getContext()));
            addView(mItems.get(i));
        }
    }

    public int getCellWidth() {
        return mCellWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int biggerSide = (getHeight() > getWidth())?getHeight():getWidth();
        mCellWidth = ((biggerSide / 2)/ NUM_COLUMNS);

        int row = 1;
        for(int i = 1; i <= this.getChildCount(); i++) {
            int col = (i + NUM_COLUMNS) % NUM_COLUMNS;
            int left = ((col == 0)?NUM_COLUMNS - 1:col - 1) * mCellWidth;
            int right = left + mCellWidth;
            int top = (row - 1) * mCellWidth;
            int bottom = top + mCellWidth;
            Square sq = (Square)getChildAt(i - 1);
            sq.setHorizPos(((col == 0)?NUM_COLUMNS:col));
            sq.setVertPos(row);
            sq.setLayoutParams(new LayoutParams(mCellWidth, mCellWidth));
            sq.layout(left, top, right, bottom);
            if(col == 0){
                row++;
            }
        }
    }
}
