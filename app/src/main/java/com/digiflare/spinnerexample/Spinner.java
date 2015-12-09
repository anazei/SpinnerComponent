package com.digiflare.spinnerexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class Spinner extends View {

    private Bitmap ball;
    private float x = 0f;
    private float y = 0f;
    private int angle = 0;

    private int ANGLE_SPEED;
    private int counter = 0;

    //the contructor must have at least two parametersto avoid any errors
    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        //this looks at values/attrs.xml to see what attributes are styleable
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Spinner);

        //Android API docs was written wrong, getInt() doesn't take the "index" value of i, it takes the property int value R.styleable.Spinner_speed


        ANGLE_SPEED = typedArray.getInt(R.styleable.Spinner_speed, 5);

        ball = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //colours the canvas background colour
        canvas.drawColor(Color.BLACK);

        angle += ANGLE_SPEED; //increment angle by attribute degrees, angle essentially controls the speed

        counter++; //increment counter from 1-360 to change the cosine radius

        if(counter >= 360){
            counter = 0;
        }

        /**
         * update compute the position of the ball
         *
         * Trig functions always take input as radians - so you can't just say Math.sin(25) as sine function of 25 degrees
         * You have to say Math.sin(25 degrees in radians)
         *
         * To convert angles to radians, you have to multiply the angle by 3.14 radians / 180 degrees since there are 3.14 radians in 180 degrees and vice versa
         *
         */

        x = (float) (Math.sin(angle * Math.PI/180) * (Math.cos(counter * Math.PI/180) * 350)) + (canvas.getWidth()/2);
        y = (float) (Math.cos(angle * Math.PI/180) * (Math.cos(counter * Math.PI/180) * 350)) + (canvas.getHeight()/2);

        //draw the ball with the new positional x, y data
        canvas.drawBitmap(ball, x, y, null);

        //the invalidate() command physically causes the onDraw() to repeat itself unless it is no longer called
        invalidate();

        //prove invalidate gets redrawn using log statement
        //Log.d("kevin", "ondraw");

    }
}
