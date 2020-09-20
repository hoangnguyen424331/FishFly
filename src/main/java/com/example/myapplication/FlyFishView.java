package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.style.TtsSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Type;

public class FlyFishView extends View {
    private int fishX=10;
    private int fishY;
    private Bitmap fish[]=new Bitmap[2];
    private int fishSpeed;
    private int canvasWidth,canvasHeigh;
    private Bitmap backgroudImage;
    private Paint scoraPaint =new Paint();
    private Bitmap life[] =new Bitmap[2];
    private int yellowX,yellowY,yellowSpod=16;
    private Paint yellowPaint =new Paint();
    private int greenX,greenY,greenSpod=20;
    private Paint greePaint=new Paint();
    private int redX,redY,redSpod=20;
    private Paint redPaint=new Paint();
    private boolean touch=false;
    private int score,lifecountfish;


    public FlyFishView(Context context) {
        super(context);

        fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        backgroudImage=BitmapFactory.decodeResource(getResources(),R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
        greePaint.setColor(Color.GREEN);
        greePaint.setAntiAlias(false);
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        scoraPaint.setColor(Color.WHITE);
        scoraPaint.setTextSize(70);
        scoraPaint.setTypeface(Typeface.DEFAULT_BOLD);
        scoraPaint.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        fishY=550;
        score=0;
        lifecountfish=3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth=canvas.getWidth();
        canvasHeigh=canvas.getHeight();

        canvas.drawBitmap(backgroudImage,0,0,null);
        int minFishY =fish[0].getHeight();
        int maxFishY=canvasHeigh-fish[0].getHeight()*3;
        fishY=fishY+fishSpeed;
        if(fishY<minFishY)
        {
            fishY=minFishY;
        }
        if(fishY>maxFishY)
        {
            fishY=maxFishY;
        }

        fishSpeed=fishSpeed+2;
        if(touch)
        {
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }



        yellowX=yellowX-yellowSpod;
        if(hitBallChocker(yellowX,yellowY))
        {
            score=score+10;
            yellowY=-100;
        }
        if(yellowX<0)
        {
            yellowX=canvasWidth+21;
            yellowY=(int) Math.floor(Math.random()*(maxFishY-minFishY)+minFishY);
        }
        canvas.drawCircle(yellowX,yellowY,10,yellowPaint);


        greenX=greenX-greenSpod;
        if(hitBallChocker(greenX,greenY))
        {
            score=score+20;
            greenY=-100;
        }
        if(greenX<0)
        {
            greenX=canvasWidth+21;
            greenY=(int) Math.floor(Math.random()*(maxFishY-minFishY)+minFishY);
        }
        canvas.drawCircle(greenX,greenY,25,greePaint);



        redX=redX-redSpod;

        if(hitBallChocker(redX,redY))
        {

            redY=-100;
            lifecountfish--;

            if(lifecountfish==0)
            {
                Toast.makeText(getContext(),"game over",Toast.LENGTH_SHORT).show();
                Intent gameOverIntent=new Intent(getContext(),GameOverActivity.class);
                gameOverIntent.putExtra("score ",score);
                getContext().startActivity(gameOverIntent);

            }
        }
        if(redX<0)
        {
            redX=canvasWidth+21;
            redY=(int) Math.floor(Math.random()*(maxFishY-minFishY)+minFishY);
        }
        canvas.drawCircle(redX,redY,30,redPaint);


        canvas.drawText("Score: "+score,20,60,scoraPaint);
        for(int i=0;i<3;i++)
        {
            int x=(int) (580+life[0].getWidth()*1.5*i);
            int y=30;
            if(i<lifecountfish)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else {
                canvas.drawBitmap(life[1],x,y,null);
            }

        }
    }

    public boolean hitBallChocker(int x,int y)
    {
        if(fishX<x&&x<(fishX+fish[0].getWidth())&&fishY<y&&y<(fishY+fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;
            fishSpeed=-22;
        }
        return true;
    }
}
