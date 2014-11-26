/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class SensorMotion {
    private Robot robot;
    private Matrix matrix;
    public int distance;
    private int posible_move;
    
    public SensorMotion(Robot father,Matrix matrix){
        this.robot = father;
        this.matrix = matrix;
        this.posible_move = Robot.DIRECTION_STOP;
    }
    public void changeDirection(){
        // AQUI VA LA LÓGICA
            
            this.distance = 9999;
            this.posible_move = Robot.DIRECTION_STOP;
            
            if(!isBusyRight() && this.robot.direction != Robot.DIRECTION_LEFT){
                if(Matrix.manhattan(new Point (this.robot.getParseX()+1,this.robot.getParseY()),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX()+1,this.robot.getParseY()),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_RIGHT;
                        System.out.println("Derecha");
                }
            }
            if(!isBusyBottom() && this.robot.direction != Robot.DIRECTION_TOP){
                if(Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()+1),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()+1),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_BOTTOM;
                        System.out.println("Abajo");
                }
            }
            if(!isBusyLeft()  && this.robot.direction != Robot.DIRECTION_RIGHT){
                if(Matrix.manhattan(new Point (this.robot.getParseX()-1,this.robot.getParseY()),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX()-1,this.robot.getParseY()),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_LEFT;
                        System.out.println("Izquierda");
                }
            }
            if(!isBusyTop()  && this.robot.direction != Robot.DIRECTION_BOTTOM){
                if(Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()-1),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()-1),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_TOP;
                        System.out.println("Arriba");
                }
            }
            
            
        
        
            
            //ejecuta
                this.robot.direction = this.posible_move;
        
        //
        // FINALIZA
        if(this.robot.getParseX()== this.robot.simulatorPanel.getFlag().x && 
                this.robot.getParseY()== this.robot.simulatorPanel.getFlag().y){
            this.robot.direction = Robot.DIRECTION_STOP;
            this.robot.actived = false;
            JOptionPane.showConfirmDialog(null, "El robot ha alcanzado su meta");
        }
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
