package kpk.dev.battleship.ui.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import kpk.dev.battleship.R;
import kpk.dev.battleship.ui.fragments.MainFragment;

/**
 * Created by krasimir.karamazov on 1/16/14.
 */
public class Square extends View {

    private Paint mStrokePaint;
    private Paint mFillPaint;
    private int mHorizPos;
    private int mVertPos;

    public Square(Context context) {
        super(context);
        init();
    }

    public Square(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Square(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(getContext().getResources().getColor(R.color.square_stroke_color));
        mStrokePaint.setStrokeWidth(2.0f);

        mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setColor(getContext().getResources().getColor(R.color.square_fill_color));
    }

    public int getHorizPos() {
        return mHorizPos;
    }

    public void setHorizPos(int mHorizPos) {
        this.mHorizPos = mHorizPos;
    }

    public int getVertPos() {
        return mVertPos;
    }

    public void setVertPos(int mVertPos) {
        this.mVertPos = mVertPos;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight()), mFillPaint);
        canvas.drawRect(new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight()), mStrokePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                Log.d(MainFragment.LOG_TAG, "Colummn" + this.getHorizPos() + ", Row:" + this.getVertPos());
                break;
        }

        return true;
    }
}
