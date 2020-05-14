/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class GamePiece {
   
    private int px; 
    private int py;
    private int width;
    private int height;
    private int vx;
    private int vy;
    private int maxX;
    private int maxY;
 
    

   
    public GamePiece(int vx, int vy, int px, int py, int width, int height, int courtWidth,
        int courtHeight) {
    	
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;

        
        this.maxX = courtWidth - width;
        this.maxY = courtHeight - height;
        
      
    }

   
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }
    
    public int getVx() {
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }

    
    public void setPx(int px) {
        this.px = px;
        clip();
    }

    public void setPy(int py) {
        this.py = py;
        clip();
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }



   
    private void clip() {
        this.px = Math.min(Math.max(this.px, 0), this.maxX);
        this.py = Math.min(Math.max(this.py, 0), this.maxY);
    }

   
    public void move() {
        this.px += this.vx;
        this.py += this.vy;

        clip();
    }

    
    public boolean touching(GamePiece that) {
    	
        return (this.px + this.width >= that.px
            && this.py + this.height >= that.py
            && that.px + that.width >= this.px 
            && that.py + that.height >= this.py);
    }


 
    

     
    public abstract void draw(Graphics g);
}