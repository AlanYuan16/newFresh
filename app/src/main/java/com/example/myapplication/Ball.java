package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Ball {
    int ballCounter=0;
    int hitCount=0;
    public int ballX,ballY,ballMovement,radius, xSpeed,  ySpeed;
    public Paint paint;

    public Ball(int x, int y, int radius,Paint paint,int speedX,int speedY ){
        this.ballX=x;
        this.ballY=y;
        this.radius = radius;
        this.paint = paint;
        this.xSpeed = speedX;
        this.ySpeed  =speedY;

    }
    public Ball(int x, int y, int radius,Paint paint ){
        this.ballX=x;
        this.ballY=y;
        this.radius = radius;
        this.paint = paint;
    }
    public void bottomBall(Canvas canvas){
        canvas.drawCircle(ballX, ballY, radius, paint);
        ballY = canvas.getHeight() - radius * 3;

    }
    public void topBall(Canvas canvas) {
        canvas.drawCircle(ballX, ballY, radius, paint);
        //speeds up and slows back down
        if(ballX>canvas.getWidth()-radius){
            xSpeed*=-2;

        }else if(ballX<radius){
            xSpeed/=-2;
        }
        ballX+=xSpeed;
        ballY= canvas.getHeight() - radius * 17 +10;

    }

    public boolean enemyShoot(Canvas canvas){
        canvas.drawCircle(ballX,ballY, radius, paint);

        ballY+=ySpeed;
        if (ballY > canvas.getHeight() - radius){
            ySpeed*=-1;
            ballCounter+=1;
        }else if(ballY<radius){
            ySpeed*=-1;
            ballCounter+=1;
        }
        if(ballCounter==5){
            return true;
        }else{
            return false;
        }

        }


   public boolean distanceForm(Ball ballStat){
        int xDiff= ballStat.ballX-ballX;
        int yDiff= ballStat.ballY-ballY;
        int answer =(int) Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2));

        if (answer < ballStat.radius+radius){
            return true;

        }else{
            return false;
        }


   }
    public boolean distanceFormTest(Ball ballStat){
        int xDiff= ballStat.ballX-ballX;
        int yDiff= ballStat.ballY-ballY;
        int answer =(int) Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2));

        if (answer < ballStat.radius+radius){
            return false;

        }else{
            return true;
        }


    }
    
   public int testForm(Ball ballInfo){
       int xDiff= ballInfo.ballX-ballX;
       int yDiff= ballInfo.ballY-ballY;
       int answer =(int) Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2));

       if (answer < ballInfo.radius+radius){
           hitCount++;

       }
       return hitCount;

   }
    public void moveRight(Ball botBall){
        botBall.ballX+=25;

    }
    public void moveLeft(Ball botBall){
        botBall.ballX-=25;

    }

    public boolean playerShoot(Canvas canvas){

        canvas.drawCircle(ballX,ballY, radius, paint);
        if (ballY > 0) {
            ballY -= ySpeed;

        }
        if(ballY < radius){

            return true;

        }
        else {

            return false;
        }

    }


    }


