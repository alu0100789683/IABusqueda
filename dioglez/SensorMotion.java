/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class SensorMotion {
    private Robot robot;
    private Matrix matrix;
    
    public Node way;
    public boolean [][] visit;
    public boolean [][] object;
    
    public int distance;
    private int posible_move;
    private boolean return_mode;
    
    public SensorMotion(Robot father,Matrix matrix){
        
        this.robot = father;
        this.matrix = matrix;
        this.renew(new Dimension(matrix.getWidth(), matrix.getHeight()));
        
        this.return_mode = false;
        this.posible_move = Robot.DIRECTION_STOP;
    }
    public void renew(Dimension size){
        
        this.way = new Node(null,null);
        this.return_mode = false;
        this.posible_move = Robot.DIRECTION_STOP;
        this.visit = new boolean[size.width][size.height];
        this.object = new boolean[matrix.getWidth()][matrix.getHeight()];
        
        for (boolean[] bs : visit) {
            for (boolean b : bs) {
                b = false;
            }    
        }
        for (boolean[] bs : object) {
            for (boolean b : bs) {
                b = false;
            }    
        }
    }
    public void changeDirection(){
        // AQUI VA LA LÓGICA
        this.posible_move = Robot.DIRECTION_STOP;
            
            if(return_mode){
                returnMove();
            }else{
                simplyMove();
            }
            
            //ejecuta
            this.robot.direction = this.posible_move;
        if(this.posible_move == Robot.DIRECTION_STOP &&
                this.robot.getParsePoint().x == this.robot.ini.x &&
                this.robot.getParsePoint().y == this.robot.ini.y){
            JOptionPane.showConfirmDialog(null, "No existe una solución ");
            this.robot.actived = false;
        }
            
        //
        // FINALIZA
        if(this.robot.getParseX()== this.robot.simulatorPanel.getFlag().x && 
                this.robot.getParseY()== this.robot.simulatorPanel.getFlag().y){
            this.robot.direction = Robot.DIRECTION_STOP;
            this.robot.actived = false;
            int resp = JOptionPane.showConfirmDialog(null, "El robot ha alcanzado su meta,  ¿Desea mostrar la ruta considerada optima por el robot?");
            if(resp == JOptionPane.OK_OPTION){
                robot.simulatorPanel.modFinalWay();
            }
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
    // ------
    public Point getTop(){
        return new Point(robot.getParseX(),robot.getParseY()-1);
    }
    public Point getRight(){
        return new Point(robot.getParseX()+1,robot.getParseY());
    }
    public Point getBottom(){
        return new Point(robot.getParseX(),robot.getParseY()+1);
    }
    public Point getLeft(){
        return new Point(robot.getParseX()-1,robot.getParseY());
    }
    // ------
    
    public void addVisit(Point p) {
        this.visit[p.x][p.y] = true;
    }

    private boolean isVisited(Point p) {
        return this.visit[p.x][p.y];
    }
    private void simplyMove(){
        
        this.way = this.way.add(new Point(this.robot.getParsePoint()));
        System.out.println("Padre"+this.way.getPrevious().getPoint());
        System.out.println("Yo"+this.way.getPoint());
        System.out.println("----");
        
        
        
            this.distance = 9999;
            this.posible_move = Robot.DIRECTION_STOP;
            
            if(!isBusyRight() && !isVisited(getRight())){
                if(Matrix.manhattan(new Point (this.robot.getParseX()+1,this.robot.getParseY()),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX()+1,this.robot.getParseY()),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_RIGHT;
                        System.out.println("RIGHT");
                }
            }
            if(!isBusyBottom() && !isVisited(getBottom())){
                if(Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()+1),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()+1),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_BOTTOM;
                        System.out.println("BOTTOM");
                }
            }
            if(!isBusyLeft() && !isVisited(getLeft())){
                if(Matrix.manhattan(new Point (this.robot.getParseX()-1,this.robot.getParseY()),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX()-1,this.robot.getParseY()),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_LEFT;
                        System.out.println("LEFT");
                }
            }
            if(!isBusyTop() && !isVisited(getTop())){
                if(Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()-1),
                    this.robot.simulatorPanel.getFlag()) < distance){
              
                        this.distance = Matrix.manhattan(new Point (this.robot.getParseX(),this.robot.getParseY()-1),
                                        this.robot.simulatorPanel.getFlag());
                        this.posible_move = Robot.DIRECTION_TOP;
                        System.out.println("TOP");
                }
            }
            // FALLO ;; MODO RETORNO
            if(this.posible_move == Robot.DIRECTION_STOP){
               this.return_mode = true;
            }
            //
    }

    private void returnMove() {
        Point p = this.robot.getParsePoint();
        Point d = this.way.getPrevious().getPoint();
        
        this.way = this.way.getPrevious();
        this.way.setNext(null);
        System.out.println("*****");
        System.out.println(p+"  - d: "+d);
        System.out.println("*****");
        if(p.x > d.x){
            this.posible_move = Robot.DIRECTION_LEFT;
            this.return_mode = false;
        }else if(p.x < d.x){
            this.posible_move = Robot.DIRECTION_RIGHT;
            this.return_mode = false;
        }else{
            if(p.y > d.y){
                this.posible_move = Robot.DIRECTION_TOP;
                this.return_mode = false;
            }else if(p.y < d.y){
                this.posible_move = Robot.DIRECTION_BOTTOM;
                this.return_mode = false;
            }
        }
    }

    void renew() {
        this.robot.simulatorPanel.showFinalWay=false;
        this.way = new Node(null,null);
        this.return_mode = false;
        this.posible_move = Robot.DIRECTION_STOP;
        this.visit = new boolean[this.matrix.getWidth()][this.matrix.getHeight()];
        
        for (boolean[] bs : visit) {
            for (boolean b : bs) {
                b = false;
            }    
        }
    }
}
