package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private Canvas canvas;
    private Button shoot, right, left;
    private boolean keepGameAlive = true;
    public  boolean enemyAlive= true;
    public  boolean playerAlive= true;
    ArrayList<Ball> shooter = new ArrayList<>();
    ArrayList<Ball> badShooter = new ArrayList<>();
    Timer time = new Timer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this);
        shoot = findViewById(R.id.shoot);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Paint paint = new Paint();
        paint.setARGB(255, 255, 200, 200);
        Paint purplePaint = new Paint();
        purplePaint.setARGB(255, 48, 18, 89);
        Paint bluePaint = new Paint();
        bluePaint.setARGB(255, 0, 0, 255);
        Ball bottomBall = new Ball(550, 200, 100, purplePaint);
        Ball topBall = new Ball(200, 200, 100, bluePaint, 20, 5);
        Paint redPaint = new Paint();
        redPaint.setARGB(255, 255, 0, 0);
        Paint yellowPaint = new Paint();
        yellowPaint.setARGB(255, 255, 234, 0);

        shoot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // System.out.println("shoot works");
                        if(playerAlive==true) {
                            shooter.add(new Ball(bottomBall.ballX, bottomBall.ballY, 100, purplePaint, 20, 8));
                        }
                        else if(playerAlive==false) {
                            System.out.println("Player can not shoot while dead");
                        }

                    }
                }
        );

        right.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // System.out.println("right wors");
                        if(playerAlive==true){
                        bottomBall.moveRight(bottomBall);
                        }
                        else if(playerAlive==false){
                            System.out.println("Can not move right if dead");
                        }
                    }
                }
        );

        left.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //System.out.println("left wors");

                        if(playerAlive==true){
                            bottomBall.moveLeft(bottomBall);
                        }
                        else if(playerAlive==false){
                            System.out.println("Can not move left if dead");
                        }
                    }
                }
        );
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                if (enemyAlive==true){
                    badShooter.add(new Ball(topBall.ballX, topBall.ballY, 100, bluePaint, 20, 8));
                }
                else if(enemyAlive==false){
                     time.cancel();
                }

            }
        };
        time.schedule(task,1000,5000);





        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int playerCounter =0;
                        int enemyCounter =0;
                        while (keepGameAlive) {
                            canvas = surfaceHolder.lockCanvas();
                            canvas.drawRGB(255,255,255);
                            bottomBall.bottomBall(canvas);
                            topBall.topBall(canvas);



                            for(int i =0 ;i< shooter.size();i++){
                                shooter.get(i).playerShoot(canvas);

                                if(shooter.get(i).playerShoot(canvas) == true){
                                    shooter.remove(i);


                                }
                                if(shooter.size()>0){
                                    if(shooter.get(i).distanceForm(topBall)==true){
                                        enemyCounter++;

                                        
                                    }
                                }
                                 /*
                                if(shooter.get(i).testForm(topBall)==5){
                                    System.out.println("please dont crash");
                                }

                                if(shooter.get(i).testForm(topBall)==5){
                                    topBall.paint=yellowPaint;
                                }
                                if(shooter.get(i).distanceForm(topBall)==true){
                                    enemyCounter++;

                                    System.out.println("crashes");
                                }*/
                                if(enemyCounter==5){
                                    topBall.paint=yellowPaint;
                                    enemyAlive=false;


                                }


                               /* if(shooter.get(i).distanceForm(topBall)==true){
                                    enemyCounter+=1;
                                }
                                if(enemyCounter==5){
                                    topBall.paint=yellowPaint;
                                }*/

                                ;
                            }

                            for(int z =0 ;z< badShooter.size();z++){
                                badShooter.get(z).enemyShoot(canvas);

                               if (badShooter.get(z).enemyShoot(canvas)==true){
                                   badShooter.remove(z);
                               }
                               if(badShooter.size()>0) {
                                   if (badShooter.get(z).distanceForm(bottomBall) == true) {
                                       playerCounter++;
                                   }
                               }
                               /*
                                if(badShooter.get(z).testForm(bottomBall)==5){
                                    bottomBall.paint=redPaint;
                                }*/
                                if(playerCounter==5){
                                    bottomBall.paint=redPaint;
                                    playerAlive=false;
                                }


                            }












                            surfaceHolder.unlockCanvasAndPost(canvas);
                            try {
                                Thread.sleep(17);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
        canvas =surfaceHolder.lockCanvas();
        canvas.drawRGB(255,255,255);
        surfaceHolder.unlockCanvasAndPost(canvas);

    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}