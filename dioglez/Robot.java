/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author eduardo
 */
public class Robot {
    
    public static final int DIRECTION_TOP = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_BOTTOM = 2;
    public static final int DIRECTION_LEFT = 3;
    public static final int DIRECTION_STOP = 4;
    
    public int direction;
    public SimulatorPanel simulatorPanel;
    public SensorMotion sensor;
    private AnimatedIcon image;
    private Point p;
    public boolean actived;
    
    public int hardMove;
    
    public Robot(SimulatorPanel father, String src,int limitx, int limity, int hardMove){
        this.direction = DIRECTION_RIGHT;
        this.p = new Point(0, 0);
        this.simulatorPanel = father;
        this.sensor = new SensorMotion(this,this.simulatorPanel.matrix);
        this.actived = false;
        
        this.hardMove = hardMove;
        this.image = new AnimatedIcon(src,limitx,limity,AnimatedIcon.DEFAULT_FRAME_DISTRIBUTION_B_L_R_T);
    }
    public Image getImage(){
        return image.getImage();
    }
    
    public int getX(){
        return p.x;
    }
    public int getY(){
        return p.y;
    }
    public int getParseX(){
        return this.simulatorPanel.matrix.parsePosition(p).x;
    }
    public int getParseY(){
        return this.simulatorPanel.matrix.parsePosition(p).y;
    }
    public Point getParsePoint(){
        return this.simulatorPanel.matrix.parsePosition(p);
    }
    // ANIMATION MOTION
    public void run(){
        if(actived){
            if(simulatorPanel.matrix.isStrictPosition(p)){
                this.direction = Robot.DIRECTION_STOP;
                sensor.changeDirection();
            }
            executeAction();
        }
    }
    public void executeAction(){
        switch(this.direction){
            case DIRECTION_TOP:
                moveTop();
            break;
            case DIRECTION_RIGHT:
                moveRight();
            break;
            case DIRECTION_BOTTOM:
                moveBottom();
            break;
            case DIRECTION_LEFT:
                moveLeft();
            break;
            //case DIRECTION_STOP:
                
            //break;
        }
    }
    public void moveTop(){
        if(!image.isMoveTop()){
            image.moveTop();
        }
        image.nextFrame();
        p.y -= hardMove;
    }
    public void moveBottom(){
        if(!image.isMoveBottom()){
            image.moveBottom();
        }
        image.nextFrame();
        p.y += hardMove;
    }
    public void moveRight(){
        if(!image.isMoveRight()){
            image.moveRight();
        }
        image.nextFrame();
        p.x += hardMove;
    }
    public void moveLeft(){
        if(!image.isMoveLeft()){
            image.moveLeft();
        }
        image.nextFrame();
        p.x -= hardMove;
    }
    public void reset() {
        this.actived = false;
        this.p = new Point(0, 0);
    }
}
