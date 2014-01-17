package kpk.dev.battleship.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import kpk.dev.battleship.ui.fragments.MainFragment;
import kpk.dev.battleship.ui.views.pieces.Ship;

/**
 * Created by krasimir.karamazov on 1/17/14.
 */
public class GameArea extends RelativeLayout {

    private int[] mLocation = new int[2];
    private float mOffsetX;
    private float mOffsetY;
    public GameArea(Context context) {
        super(context);
    }

    public GameArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startAddingPieces(int cellWidth) {
        Ship ship = new Ship(getContext());
        ship.setNumSquares(5);
        ship.setSquareWidth(cellWidth);
        ship.setOrientation(Ship.Orientation.VERTICAL);
        addView(ship);
        ship.setOnTouchListener(getOnTouchListener());
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
                        params.topMargin = (int)posY;
                        params.leftMargin = (int)posX;
                        view.setLayoutParams(params);
                        break;
                }
                return true;
            }
        };
    }
}
