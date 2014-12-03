/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author eduardo
 */
public class Matrix {
    
    public boolean [][] busy;
    private final SimulatorPanel panel;
    private Dimension size;
    private int span;
    
    public Matrix(int span, Dimension size, SimulatorPanel panel){
        this.busy = new boolean[size.width][size.height];
        
        
        initBusy();
        this.span = span;
        this.size = size;
        this.panel = panel;
     
    }
    private void initBusy(){
        for (int i = 0 ; i<busy.length ; i++) {
           for (int j = 0 ; j<busy[i].length; j++) {
               busy[i][j] = false;
           } 
        }
    }
    public void makeRandomBusy(){
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0 ; i<busy.length ; i++) {
            for (int j = 0 ; j<busy[i].length; j++) {
                if(rnd.nextDouble() < 0.2){
                    busy[i][j] = true;
                }else{
                    busy[i][j] = false;
                }
            } 
        } 
    }
    public void print(Graphics g){
        // print bounds
        Point p = screanerPosition(new Point(size.width,size.height));
        
        g.setColor(Color.RED);
        g.drawRect(0, 0, p.x, p.y);
        // print matrix
        for(int i = 0;i < p.x;i+=this.span){
            g.drawLine(i, 0, i, p.y);
        }
        for(int i = 0; i< p.y;i+=this.span){
            g.drawLine(0, i, p.x, i);
        }
    }
    
    public Point parsePosition(Point p) {
        return new Point(p.x/span, p.y/span);
    }
    public Point screanerPosition(Point p) {
        return new Point(p.x*span, p.y*span);
    }
    public boolean isStrictPosition(Point p){
        if(p.x%span == 0 && p.y%span ==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isBusy(Point p){
        if(p.x >= 0 && p.y >= 0 && p.x < size.width && p.y < size.height){
            return busy[p.x][p.y]; 
        }else{
            return true;
        }
    }

    void updateSize(int span, Dimension size) {
        Point parsep = parsePosition(new Point(size.width,size.height));
        
        this.busy = new boolean[parsep.x][parsep.y];
        this.panel.robot.sensor.renew(size);
        
        initBusy();
        
        this.span = span;
        this.size = new Dimension(parsep.x,parsep.y);
        
        
    }

    int getWidth() {
        return size.width;
    }

    int getHeight() {
        return size.height;
    }
    public static int manhattan(Point a, Point b){
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
