// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.mobilecoderz.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.mobilecoderz.demo.R;


public class PinView extends SubsamplingScaleImageView
{

    private Bitmap pin;
    private PointF sPin;
    Paint paint = new Paint();
    public PinView(Context context)
    {
        this(context, null);
    }
    public PinView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        initialise();
    }

    private void initialise()
    {
        float f2 = getResources().getDisplayMetrics().densityDpi;
        pin = BitmapFactory.decodeResource(getResources(), R.drawable.pushpin_blue);
        float f = f2 / 420F;
        float f1 = pin.getWidth()/2;
        f2 /= 420F;
        float f3 = pin.getHeight()/2;
        pin = Bitmap.createScaledBitmap(pin, (int)(f * f1), (int)(f2 * f3), true);
    }

    public PointF getPin()
    {
        return sPin;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (isReady())
        {
            paint.setAntiAlias(true);
            if (sPin != null && pin != null)
            {
                PointF pointf = sourceToViewCoord(sPin);
                float f = pointf.x;
                float f1 = pin.getWidth() / 2;
                float f2 = pointf.y;
                float f3 = pin.getHeight();
                canvas.drawBitmap(pin, f - f1, f2 - f3, paint);
               // return;
            }
        }
    }
    public void setPin(PointF pointf)
    {
        sPin = pointf;
        initialise();
        invalidate();
    }
}
