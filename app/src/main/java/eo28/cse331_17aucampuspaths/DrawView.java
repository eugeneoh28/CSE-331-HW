package eo28.cse331_17aucampuspaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import hw8.*;

/**
 * Created by Eugene on 12/5/2017.
 */

public class DrawView extends AppCompatImageView {

    private Boolean dStart = false;
    private Boolean dEnd = false;
    private Boolean dPath = false;
    private List<CampusPoint> nodes = new ArrayList<>();
    private float startX = 0f;
    private float startY = 0f;
    private float endX = 0f;
    private float endY = 0f;
    private float scale = 0.25f;


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
        paint.setStrokeWidth(5f);
        if (dPath) {
            float startX = Double.valueOf(nodes.get(0).getX()).floatValue() * scale;
            float startY = Double.valueOf(nodes.get(0).getY()).floatValue() * scale;
            for (int i = 0; i < nodes.size(); i++) {
                float endX = Double.valueOf(nodes.get(i).getX()).floatValue() * scale;
                float endY = Double.valueOf(nodes.get(i).getY()).floatValue() * scale;
                canvas.drawLine(startX, startY, endX, endY, paint);
                startX = endX;
                startY = endY;
            }
        }

        if (dStart) {
            canvas.drawCircle(startX, startY, 10.f, paint);
        }
        if (dEnd) {
            paint.setColor(Color.BLUE);
            canvas.drawCircle(endX, endY, 10.f, paint);
        }
    }

    public void drawPath(List<CampusPoint> nodes) {
        dPath = true;
        this.nodes = nodes;
        this.invalidate();
    }

    public void drawStart(float x, float y) {
        dStart = true;
        startX = x * scale;
        startY = y * scale;
        this.invalidate();
    }

    public void drawEnd(float x, float y) {
        dEnd = true;
        endX = x * scale;
        endY = y * scale;
        this.invalidate();
    }

    public void resetMap() {
        dPath = false;
        dStart = false;
        dEnd = false;
        this.invalidate();
    }
}

