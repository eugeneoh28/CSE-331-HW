package eo28.cse331_17aucampuspaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Eugene on 12/5/2017.
 */

public class DrawView extends AppCompatImageView {

    private Boolean drawCircle = false;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        if (drawCircle) {
            canvas.drawCircle(50.f, 50.f, 50.f, paint);
        }
    }

    public void toggleDrawCircle() {
        drawCircle = !drawCircle;
        this.invalidate();
    }
}

