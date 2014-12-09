/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class MouseController implements MouseListener{
    
    public int i=0;
    public MainFrame frame;
    
    public MouseController(MainFrame frame){
        super();
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if(e.getSource().getClass() == JMenuItem.class){
            JMenuItem item = (JMenuItem)e.getSource();
            switch(item.getName()){
                case "NewItemBar":
                    String strW = JOptionPane.showInputDialog("Seleccionar Ancho del área de trabajo");
                    String strH = JOptionPane.showInputDialog("Seleccionar Alto del área de trabajo");
                    if(strW != null || strH != null){
                        int width = Integer.parseInt(strW);
                        int height = Integer.parseInt(strH);

                        //upload robot
                        frame.matrixPanel.robot.reset();
                        frame.matrixPanel.updateSize(new Dimension(width,height), 32);
                        //frame.matrixPanel.destroy();
                        frame.matrixPanel.print(true,true,true);
                        frame.scrollPanel.revalidate();
                        frame.scrollPanel.repaint();  
                    }
                break;
                case "SaveItemBar":
                    i++;
                    //frame.matrixPanel.robot.changeFrame(i);
                break;
                case "ExitItemBar":
                    //frame.matrixPanel.destroy();
                    //frame.dispose();
                    
                    
                    
                break;  
                case "RunItemBar":
                    
                    if (frame.matrixPanel.robot.actived) {
                        frame.matrixPanel.robot.actived=false;
                    }else{
                        // Inicialize ControlPanel
                        ControlPanel controlPanel = new ControlPanel(frame.matrixPanel.span);
                        int option = JOptionPane.showConfirmDialog(null, controlPanel, 
                            "Indicar valores de reproducción", JOptionPane.OK_CANCEL_OPTION);
                        
                        if(option == JOptionPane.OK_OPTION){
                            
                            HashMap<String,Integer> map = controlPanel.getValues();
                            frame.matrixPanel.robot.hardMove = map.get("sliderHard");
                            frame.matrixPanel.robot.sensor.distance = frame.matrixPanel.matrix.getWidth() + frame.matrixPanel.getHeight();
                            frame.matrixPanel.robot.actived=true;
                        }
                    }
                    
                break;
                case "ShowMatrixItemBar":
                    if (frame.matrixPanel.showMatrix) {
                        frame.matrixPanel.showMatrix = false;
                    }else{
                        frame.matrixPanel.showMatrix = true;
                    }
                break;
                case "RandomItemBar":
                    frame.matrixPanel.matrix.makeRandomBusy();
                break;
                case "FinishInsertItemBar":
                    frame.matrixPanel.modFinish();
                break;
                case "MovePjItemBar":
                    frame.matrixPanel.modMovePj();
                break;
                default:
                    JOptionPane.showMessageDialog(frame, "Aún no se ha definido esa funcionalidad","Error",JOptionPane.ERROR_MESSAGE);
                break;
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
