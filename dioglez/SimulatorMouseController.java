/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author eduardo
 */
public class SimulatorMouseController implements MouseListener{
    
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
            if(!panel.matrix.busy[p.x][p.y]){
                panel.addObject(p);
            }else{
                panel.matrix.busy[p.x][p.y] = false;
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
}
