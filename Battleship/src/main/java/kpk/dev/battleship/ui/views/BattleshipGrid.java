package kpk.dev.battleship.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import java.util.LinkedList;
import java.util.List;

import kpk.dev.battleship.R;

/**
 * Created by krasimir.karamazov on 1/16/14.
 */
public class BattleshipGrid extends RelativeLayout {
    private static final int NUM_COLUMNS = 11;
    private static final int NUM_ROWS = 11;
    private List<Square> mItems;

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
        }
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int numRows = 0;
        int numCols = 0;
        for(int i = 0; i < NUM_ROWS; i++) {
            for(int j = 0; j < NUM_COLUMNS; j++) {
                
            }
        }
    }
}
