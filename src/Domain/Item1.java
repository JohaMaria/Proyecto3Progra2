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
public class Item1 extends Thread{
    int posX,posY;
    Image image;
    Maze laberinto;
    int posiion;
    int posiionFila;
    boolean vuelta;
    boolean entrada=true;
    boolean vuelta2;
    boolean entrada2=false;
    boolean vertical=true;
    boolean horizontal;
    
    public boolean murio=true;
    public Item1(int posX,int posY,Maze maze){
        this.posX=posX;
        this.posY=posY;
        this.posiion=0;
        this.laberinto=maze;
        this.posiionFila=0;
        try {
            this.image=new Image(new FileInputStream("src/assets/11.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Item1.class.getName()).log(Level.SEVERE, null, ex);
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
    
   
    public void move(){
        if(vertical){
            if(posX==0&&laberinto.matriz[posY][posX+1].getCondition()==0)
                vertical=false;
            else if(posX>0&&posX<20&&laberinto.matriz[posY][(posX+1)].getCondition()==0&&laberinto.matriz[posY][(posX-1)].getCondition()==0)
                vertical=false;
//            if(laberinto.matriz[posY][(posX-1)].getEstado()==0)
//                vertical=true;
            if(posX<20&&(laberinto.matriz[posY][(posX+1)].getCondition()==1||laberinto.matriz[posY][(posX+1)].getCondition()
                    ==3)&&entrada){
                posX=posX+1;
                entrada2=false;
            }
            else{
                vuelta=true;
                entrada=false;
            }
            if(posX>0&&vuelta&&(laberinto.matriz[posY][posX-1].getCondition()==1||laberinto.matriz[posY][(posX-1)].getCondition()==3)){
                posX=posX-1;
                entrada2=false;
            }
            else{
                entrada=true;
                vuelta=false;
//                vertical=false;
            }
        }
        else{
            if(posY<13&&(laberinto.matriz[posY+1][posX].getCondition()==1||laberinto.matriz[posY+1][(posX)].getCondition()==3)&&entrada2){
                posY=posY+1;
            }
            else{
                vuelta2=true;
                entrada2=false;
            }
            
            if(posY>0&&(laberinto.matriz[posY-1][posX].getCondition()==1||laberinto.matriz[posY-1][(posX)].getCondition()==3)&&vuelta2)
                posY=posY-1;
            else{
                entrada2=true;
                vuelta2=false;
            }
        }
    }
    
    @Override
    public void run(){
        while(murio){
            move();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Item1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
