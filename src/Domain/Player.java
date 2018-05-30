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
public class Player {
    public Image image;
    public int x,y;
    public int i,j;
    
    public Player(){
        try {
            this.image=new Image(new FileInputStream("src/assets/carro.png"));
            this.x=0;
            this.y=0;
            this.i=0;
            this.j=0;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public Player(int x,int y,int i,int j){
        try {
            this.image=new Image(new FileInputStream("src/assets/carro.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x=x;
        this.y=y;
        this.i=i;
        this.j=j;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    void draw(GraphicsContext gc){
        gc.drawImage(image, x, y, 40, 40);
    }
}
