package kpk.dev.battleship.ui.views.pieces;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import kpk.dev.battleship.ui.fragments.MainFragment;
import kpk.dev.battleship.ui.views.pieces.builders.ShipBuilder;

/**
 * Created by krasimir.karamazov on 1/17/14.
 */
public class Ship extends View {
    private int mSquareWidth;
    private int mPosition;
    private Paint mShipPaint;
    private int mColor;
    private ShipData mData;

    public Ship(Context context, ShipData data) {
        super(context);
        data = mData;
        init();
    }

    private void init() {
        mShipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShipPaint.setStyle(Paint.Style.FILL);
        mShipPaint.setColor(Color.RED);
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public int getSquareWidth() {
        return mSquareWidth;
    }

    public void setSquareWidth(int mSquareWidth) {
        this.mSquareWidth = mSquareWidth;
    }

    public String getName() {
        return mData.getName();
    }

    public int getNumSquares() {
        return mData.getNumSquares();
    }

    public ShipBuilder.Orientation getOrientation() {
        return mData.getOrientation();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        if(mData.getOrientation().equals(ShipBuilder.Orientation.HORIZONTAL)){
            canvas.drawRect(new Rect(0, 0, width * mData.getNumSquares(), width), mShipPaint);
        }else{
            canvas.drawRect(new Rect(0, 0, width, width * mData.getNumSquares()), mShipPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;
        if(mData.getOrientation().equals(ShipBuilder.Orientation.HORIZONTAL)){
            width = mSquareWidth * mData.getNumSquares();
            height = mSquareWidth;
        }else{
            width = mSquareWidth;
            height = mSquareWidth * mData.getNumSquares();
        }
        setMeasuredDimension(width, height);
    }
}
