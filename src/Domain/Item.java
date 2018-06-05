/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Jesus
 */
public class Item {
    int posX,posY;
    Image image;
    
    public Item(int posX,int posY){
        this.posX=posX;
        this.posY=posY;
        try {
            this.image=new Image(new FileInputStream("src/assets/item1.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public void draw(GraphicsContext gc){
        gc.drawImage(this.image, this.posX*40+10, this.posY*40+10,20,20);
    }
}
