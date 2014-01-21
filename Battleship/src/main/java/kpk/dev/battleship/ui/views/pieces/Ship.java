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

/**
 * Created by krasimir.karamazov on 1/17/14.
 */
public class Ship extends View {

    public enum Orientation{
        VERTICAL, HORIZONTAL;
    }
    private Paint mShipPaint;
    private int mNumSquares;
    private int mSquareWidth;
    private int mPosition;
    private String mName;
    private Orientation mOrientation;
    private int mColor;
    private boolean mMoving;
    private int offsetX = 0;
    private int offsetY = 0;
    private int[] mLocation = new int[2];
    public Ship(Context context) {
        super(context);
        init();
    }

    public Ship(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Ship(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getNumSquares() {
        return mNumSquares;
    }

    public void setNumSquares(int mNumSquares) {
        this.mNumSquares = mNumSquares;
    }

    public int getSquareWidth() {
        return mSquareWidth;
    }

    public void setSquareWidth(int mSquareWidth) {
        this.mSquareWidth = mSquareWidth;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public Orientation getOrientation() {
        return mOrientation;
    }

    public void setOrientation(Orientation mOrientation) {
        this.mOrientation = mOrientation;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        if(mOrientation.equals(Orientation.HORIZONTAL)){
            canvas.drawRect(new Rect(0, 0, width * mNumSquares, width), mShipPaint);
        }else{
            canvas.drawRect(new Rect(0, 0, width, width * mNumSquares), mShipPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;
        if(mOrientation.equals(Orientation.HORIZONTAL)){
            width = mSquareWidth * mNumSquares;
            height = mSquareWidth;
        }else{
            width = mSquareWidth;
            height = mSquareWidth* mNumSquares;
        }
        setMeasuredDimension(width, height);
    }
}
