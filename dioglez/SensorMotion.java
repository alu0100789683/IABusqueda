/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Point;

/**
 *
 * @author eduardo
 */
public class SensorMotion {
    private Robot robot;
    private Matrix matrix;
    
    public SensorMotion(Robot father,Matrix matrix){
        this.robot = father;
        this.matrix = matrix;
    }
    public void changeDirection(){
        // AQUI VA LA LÓGICA
        
        /*
        if(isBusyBottom()){
            this.robot.direction = Robot.DIRECTION_RIGHT;
            if(isBusyRight()){
                this.robot.direction = Robot.DIRECTION_TOP;
            }
        }
        if(!isBusyBottom()){
            this.robot.direction = Robot.DIRECTION_BOTTOM;
        }*/
        
        //this.robot.direction = heuristic.reload();
        
        
        // ---------
    }
    public Point getPosition(){
        return new Point(robot.getParseX(),robot.getParseY());
    }
    public boolean isBusyTop(){
        return matrix.isBusy(new Point(robot.getParseX(),robot.getParseY()-1));
    }
    public boolean isBusyRight(){
        return matrix.isBusy(new Point(robot.getParseX()+1,robot.getParseY()));
    }
    public boolean isBusyBottom(){
        return matrix.isBusy(new Point(robot.getParseX(),robot.getParseY()+1));
    }
    public boolean isBusyLeft(){
        return matrix.isBusy(new Point(robot.getParseX()-1,robot.getParseY()));
    }
}
