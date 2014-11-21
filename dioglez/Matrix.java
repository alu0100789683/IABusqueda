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

/**
 *
 * @author eduardo
 */
public class Matrix {
    
    public boolean [][] busy;
    private SimulatorPanel panel;
    public Dimension size;
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
    public void print(Graphics g){
        // print bounds
        g.setColor(Color.RED);
        g.drawRect(0, 0, size.width, size.height);
        // print matrix
        for(int i = 0;i < size.width;i+=this.span){
            g.drawLine(i, 0, i, size.height);
        }
        for(int i = 0; i< size.height;i+=this.span){
            g.drawLine(0, i, size.width, i);
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
        if(p.x <= parsePosition(new Point(size.width,size.height)).x - 1
                && p.y <= parsePosition(new Point(size.width,size.height)).y - 1){
            return busy[p.x][p.y]; 
        }else{
            return true;
        }
    }

    void updateSize(int span, Dimension size) {
        this.busy = new boolean[size.width][size.height];
        initBusy();
        this.span = span;
        this.size = size;
    }

    
}
