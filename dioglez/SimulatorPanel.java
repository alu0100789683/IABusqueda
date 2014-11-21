/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author eduardo
 */
public class SimulatorPanel extends JPanel{
    public Image background;
    public Image obstacle;
    public Robot robot;
    
    private Dimension size;
    private int span;
    
    private boolean printTerrain;
    private boolean printObject;
    private boolean printRobot;
    
    public boolean showMatrix;
    public Matrix matrix;
    
    public SimulatorPanel(Dimension size, int span){
        super();
        
        this.showMatrix = false;
        this.span = span;
        this.size = convetDimension(size, span);
        this.setPreferredSize(this.size);
        this.matrix = new Matrix(this.span, getPreferredSize(),this);
        
        
        this.printTerrain = false;
        this.printObject = false;
        this.printRobot = false;
        
        this.addMouseListener(new SimulatorMouseController(this));
        
    }
    @Override
    public void paintComponent(Graphics g){
        
        //super.paintComponent(g);
        
        // STATE prepare terraint
        if(printTerrain){
            for (int x = 0; x < getWidth(); x += background.getWidth(this)) {
                for (int y = 0; y < getHeight(); y += background.getHeight(this)) {
                    g.drawImage(background, x, y, background.getWidth(this), background.getHeight(this), this);
                }
            }
        }
        
        //STATE Generate random object
        if(printObject){
            for (int i = 0; i < matrix.size.getWidth(); i++) {
                for (int j = 0; j < matrix.size.getHeight(); j++) {
                    if(matrix.busy[i][j]){
                        g.drawImage(obstacle, matrix.screanerPosition(new Point(i,j)).x,matrix.screanerPosition(new Point(i,j)).y, obstacle.getWidth(this), obstacle.getHeight(this), this);
                    }
                }
            }
             
        }
        if(printRobot){
            g.drawImage(robot.getImage(), robot.getX(), robot.getY(), robot.getImage().getWidth(this), robot.getImage().getHeight(this), this);
        }
        if(showMatrix){
            matrix.print(g);
        }
        
    }
    public void print(boolean terrain, boolean object, boolean robot){
        this.printTerrain = terrain;
        this.printObject = object;
        this.printRobot = robot;
        this.repaint();
    }
    public void init(String backgroundStr,
            String obstacleStr,
            String robotStr, 
            int robotLimitx, 
            int robotLimity, 
            int hardMove,
            int span){
        
        this.span = span;
        this.background = Toolkit.getDefaultToolkit().createImage(backgroundStr);
        
        this.robot = new Robot(this,robotStr,robotLimitx, robotLimity, hardMove);
        this.obstacle = Toolkit.getDefaultToolkit().createImage(obstacleStr);
        
        /*
        SE SUSTITUYE POR UN OBSTACULO
        this.obstacle = new Image[obstacleStr.length];
        for(int i=0; i< obstacleStr.length ;i++){
           obstacle[i] = Toolkit.getDefaultToolkit().createImage(obstacleStr[i]);
        }*/
        
        
    }
    public void generateRandomObject(){
        
        this.repaint();
    }
    public void destroy(){
    
    }

    private Dimension convetDimension(Dimension size, int span) {
        
        return new Dimension(size.width*span, size.height*span);
        
    }
    public void updateSize(Dimension size,int span){
        this.span = span;
        this.size = convetDimension(size, span);
        this.setPreferredSize(this.size);
        this.matrix.updateSize(this.span, getPreferredSize());
    }

    void addObject(Point p) {
        matrix.busy[p.x][p.y] = true;
    }
}
