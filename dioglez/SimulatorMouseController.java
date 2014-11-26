/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author eduardo
 */
public class SimulatorMouseController implements MouseMotionListener, MouseListener{
    
    public SimulatorPanel panel;
    
    public SimulatorMouseController(SimulatorPanel panel){
        this.panel = panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().getClass() == SimulatorPanel.class){
            Point p = panel.matrix.parsePosition(e.getPoint());
            if(panel.isFinish()){
                if(panel.matrix.busy.length > p.x 
                        && panel.matrix.busy[p.x].length > p.y){
                    panel.addObjectFinish(p);
                    panel.modFinish();
                }
            }else{
                if(panel.matrix.busy.length > p.x 
                        && panel.matrix.busy[p.x].length > p.y){
                    if(!panel.matrix.busy[p.x][p.y]){
                        panel.addObject(p);
                    }else{
                        panel.matrix.busy[p.x][p.y] = false;
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(panel.isMoveFlag){   
            panel.moveFlag(e.getPoint());
        }
    }
}
