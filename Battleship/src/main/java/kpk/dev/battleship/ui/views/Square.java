package kpk.dev.battleship.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import kpk.dev.battleship.R;

/**
 * Created by krasimir.karamazov on 1/16/14.
 */
public class Square extends View {

    private Paint mStrokePaint;
    private Paint mFillPaint;
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

        mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setColor(getContext().getResources().getColor(R.color.square_fill_color));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight()), mFillPaint);
        canvas.drawRect(new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight()), mStrokePaint);
    }
}
