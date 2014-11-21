/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author eduardo
 */
public class AnimatedIcon extends Image{   
    public static final int DEFAULT_FRAME_DISTRIBUTION_B_L_R_T = 0;
    
    
    private BufferedImage source_img;
    private Image anim_img;
    
    private int limit_x;
    private int limit_y;
    
    private int indexFrame;
    private int typeFrame;
    
    private int size_frame_x;
    private int size_frame_y;
    
    
    private int size_x;
    private int size_y;
    
    private int anim_x;
    private int anim_y;
    
    public AnimatedIcon(String src, 
                        int limit_x, 
                        int limit_y,
                        int type_frame){
        super();
        
        try {
            
            this.source_img = ImageIO.read(new File(src));
            
            this.limit_x = limit_x;
            this.limit_y = limit_y;
            this.indexFrame = 0;
            this.typeFrame = type_frame;
            
            this.anim_x = 0;
            this.anim_y = 0;
            
            this.size_x = source_img.getWidth();
            this.size_y = source_img.getHeight();
            
            this.size_frame_x = size_x/limit_x;
            this.size_frame_y = size_y/limit_y;
          
            this.anim_img = source_img.getSubimage(anim_x, anim_y, size_frame_x, size_frame_y);
           
        } catch (IOException ex) {
            Logger.getLogger(AnimatedIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Image getImage(){
        return anim_img;
    }
    @Override
    public int getWidth(ImageObserver observer) {
        return size_x;
    }

    @Override
    public int getHeight(ImageObserver observer) {
        return size_y;
    }

    @Override
    public ImageProducer getSource() {
        return (ImageProducer) source_img;
    }

    @Override
    public Graphics getGraphics() {
        return this.getGraphics();
    }

    @Override
    public Object getProperty(String name, ImageObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // ANIMATION METHOD
    public void nextFrame() {
        anim_img = source_img.getSubimage(anim_x, anim_y, size_frame_x, size_frame_y);
        this.anim_x += size_frame_x;
        
        if(anim_x >= size_x){
            this.anim_x = 0;
        }
    }
    private void changeFrame(int index){
        this.anim_y = index * size_frame_y;
        if(anim_y >= size_y){
            this.anim_y = 0;
        }
    }
    private boolean isFrame(int i) {
        return anim_y == i * size_frame_y;
    }
    public void moveRight(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            changeFrame(2);
        }
    }
    public void moveLeft(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            changeFrame(1);
        }
    }
    public void moveTop(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            changeFrame(3);
        }
    }
    public void moveBottom(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            changeFrame(0);
        }
    }
    public boolean isMoveRight(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            return isFrame(2);
        }else{
            return false;
        }
    }
    public boolean isMoveLeft(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            return isFrame(1);
        }else{
            return false;
        }
    }
    public boolean isMoveTop(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            return isFrame(3);
        }else{
            return false;
        }
    }
    public boolean isMoveBottom(){
        if(typeFrame == DEFAULT_FRAME_DISTRIBUTION_B_L_R_T){
            return isFrame(0);
        }else{
            return false;
        }
    }
}
