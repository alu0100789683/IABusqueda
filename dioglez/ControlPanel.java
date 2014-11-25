/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;

/**
 *
 * @author eduardo
 */
class ControlPanel extends JPanel{
    private JSlider sliderHard;
    private JLabel labelHard;
    
    private final int maxHard;
    
    public ControlPanel(int maxHard){
        super();
        this.maxHard = maxHard;
        
        initComponent();
        addComponent();
        
    }

    private void initComponent() {
        this.setLayout(new GridLayout(2, 1));
        labelHard = new JLabel("Velocidad");
        sliderHard = new JSlider(0, 2, 0);
        
    }

    private void addComponent() {
        this.add(labelHard);
        this.add(sliderHard);
    }

    public HashMap getValues() {
        
        // 0 -> 1
        // 1 -> 8
        // 2 -> 16
        // 3 -> 32
        
        HashMap<String,Integer> map = new HashMap<>();
        if(maxHard==32){
            switch(sliderHard.getValue()){
                case 0:
                    map.put("sliderHard", 1);
                break;
                case 1:
                    map.put("sliderHard", 8);
                break;
                case 2:
                    map.put("sliderHard", 16);
                break;
                case 3:
                    map.put("sliderHard", 32);
                break;
            }
        }else{
            map.put("sliderHard", 1);
        }
        return map;
    }
}
