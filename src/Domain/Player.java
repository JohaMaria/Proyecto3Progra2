/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Johanna
 */
public class Player extends Thread{
    public Image image;
    public int x,y;
    public int i,j;
    int previousI=0;
    int previousJ=0;
    Block[][] matriz;
    boolean collision=false;
    boolean collision2=false;
    int collisionI=0,collisionJ=0;
    private ArrayList<Image> sprite;
    public int count;
    public boolean noGain;
    public boolean pause=true;
    
    public Player(){
        
            this.sprite = new ArrayList<Image>();
            this.x=0;
            this.y=0;
            this.i=0;
            this.j=0;
            this.count=0;
            this.noGain=true;
      
    
    }
    
    public Player(int x,int y,Block[][] m,int count){
        this.sprite = new ArrayList<Image>();
        this.x=x;
        this.y=y;
        this.i=y/40;
        this.j=x/40;
        this.matriz=m;
        this.count=count;
        this.noGain=true;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList<Image> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<Image> sprite) {
        this.sprite = sprite;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void draw(GraphicsContext gc){
        gc.drawImage(image, x, y, 40, 40);
    }
    
public int move(){
        boolean pase=true;
        boolean a=false;
        boolean b=false;
        boolean c=false;
        boolean d=false;
        boolean noExit=true;
        int posI=0;
        int posJ=0;
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                if((this.i==i&&this.j==j)&&pase){
                    if(matriz[i][j].getCondition()==5){
                        this.noGain=false;    
                    }
                    posI=i;
                    posJ=j;
                    if(j<19&&(matriz[i][j+1].getCondition()==1||matriz[i][j+1].getCondition()==5)&&(previousI!=i||previousJ!=j+1)){
                        a=true;
//                        previousI=i;
//                        anteriosJ=j;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                        pase=false;
                        noExit=false;
                    }
                    if(j>0&&(matriz[i][j-1].getCondition()==1||matriz[i][j-1].getCondition()==5)&&(previousI!=i||previousJ!=j-1)){
                        b=true;
//                        previousI=i;
//                        anteriosJ=j;
                        pase=false;
                        noExit=false;
//                        paraDonde=2;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                    }
                    if(i<12&&(matriz[i+1][j].getCondition()==1||matriz[i+1][j].getCondition()==5)&&(previousI!=i+1||previousJ!=j)){
                        c=true;
//                        previousI=i;
//                        anteriosJ=j;
                        pase=false;
                        noExit=false;
//                        paraDonde=3;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                    }
                    if(i>0&&(matriz[i-1][j].getCondition()==1||matriz[i-1][j].getCondition()==5)&&(previousI!=i-1||previousJ!=j)){
                        d=true;
//                        previousI=i;
//                        anteriosJ=j;
                        pase=false;
                        noExit=false;
//                        paraDonde=4;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                    }
                    if(collision){
                        matriz[collisionI][collisionJ].setCondition(1);
                        collision=false;
                    }
                    if(noExit){
                        if(choque(i, j)){
//                            matriz[i][j].setCondition(0);
                            collisionJ=j;
                            collisionI=i;
//                            collision=true;
                            collision2=true;
                        }
                        posI=previousI;
                        posJ=previousJ;
                        previousI=i;
                        previousJ=j;
                        pase=false;
//                        if(cond==1)
//                            a=true;
//                        else if(cond==2)
//                            b=true;
//                        else if(cond==3)
//                            c=true;
//                        else if(cond==4)
//                            d=true;
//                        
                    }
                }
            }
        }
        
        for(int x=0;x<20;x++){
            Random ran=new Random();
            int num=(int)(ran.nextDouble()*4+1);
            switch(num){
                case 1:
                    if(a){
                        matriz[posI][posJ].setCondition(1);
                        if(matriz[posI][posJ+1].getCondition()!=5)
                            matriz[posI][posJ+1].setCondition(3);
                        setX(matriz[posI][posJ+1].getX());
                        previousI=posI;
                        previousJ=posJ;
                        this.j++;
                        if(collision2){
                            matriz[posI][posJ].setCondition(4);
                            collision=true;
                            collision2=false;
                        }
                        x=30;
//                        break;
                        return 4;
                        
                    }else
                        break;
                    
                case 2:
                    if(b){
                        matriz[posI][posJ].setCondition(1);
                        if(matriz[posI][posJ-1].getCondition()!=5)
                            matriz[posI][posJ-1].setCondition(3);
                        
                        setX(matriz[posI][posJ-1].getX());
                        previousI=posI;
                        previousJ=posJ;
                        this.j--;
                        if(collision2){
                            matriz[posI][posJ].setCondition(4);
                            collision=true;
                            collision2=false;
                        }
                        x=30;
//                        break;
                        return 2;
                    }else
                        break;
                
                case 3:
                    if(c){
                        matriz[posI][posJ].setCondition(1);
                        if(matriz[posI+1][posJ].getCondition()!=5)
                            matriz[posI+1][posJ].setCondition(3);
                        
                        setY(matriz[posI+1][posJ].getY());
                        previousI=posI;
                        previousJ=posJ;
                        this.i++;

                        if(collision2){
                            matriz[posI][posJ].setCondition(4);
                            collision=true;
                            collision2=false;
                        }
                        x=30;
//                        break;
                        return 0;
                    }else
                        break;
//                    
                case 4:
                    if(d){
                        matriz[posI][posJ].setCondition(1);
                        if(matriz[posI-1][posJ].getCondition()!=5)
                            matriz[posI-1][posJ].setCondition(3);
                        
                        setY(matriz[posI-1][posJ].getY());
                        previousI=posI;
                        previousJ=posJ;
                        this.i--;
                        if(collision2){
                            matriz[posI][posJ].setCondition(4);
                            collision=true;
                            collision2=false;
                        }
                        x=30;
//                        break;
                        return 6;
                    }else
                        break;
            }
        }
        return 0;
    }
    
    public void cambiarImagen(){
            try {
                this.image=new Image(new FileInputStream("src/assets/carrito.png"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public boolean choque(int i,int j){
        if(j<19&&matriz[i][j+1].getCondition()==3)
            return true;
        else if(j>0&&matriz[i][j-1].getCondition()==3)
            return true;
        else if(i<12&&matriz[i+1][j].getCondition()==3)
            return true;
        else if(i>0&&matriz[i-1][j].getCondition()==3)
            return true;
        else 
            return false;
    }
}

