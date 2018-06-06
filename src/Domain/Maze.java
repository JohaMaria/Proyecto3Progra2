/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jesus
 */
public class Maze {
    public Block[][] matriz;
    private Block wall;
    private Block road;
    Map map;
    int num=1;
//    int posX=0;
//    int posY=80;
    
    public Maze(int n){
//        this.matriz=new Block[10][10];
        map=new Map(n);
        this.matriz=map.matriz;

    }
    
    public void piaintMatriz(GraphicsContext gc){
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                if(matriz[i][j].getCondition()==0){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(40*j, 40*i, 40, 40);
                }
                else if(matriz[i][j].getCondition()==1){
                    gc.setFill(Color.WHITE);
                    gc.fillRect(40*j, 40*i, 40, 40);
                }
                else if(matriz[i][j].getCondition()==4){
                    gc.setFill(Color.WHITE);
                    gc.fillRect(40*j, 40*i, 40, 40);
                }
                 else if(matriz[i][j].getCondition()==5){
                    gc.setFill(Color.RED);
                    gc.fillRect(40*j, 40*i, 40, 40);
                }
            }
        }
    }           
    public Block[][] getMatriz(){
        return matriz;
    }
    
    public void mouse(int x, int y){
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                if ((x>=j*40&&x<=(j+1)*40)&&(y>=i*40&&y<=(i+1)*40)) {
                    if(matriz[i][j].getCondition()==0){
                        System.out.println(matriz[i][j].getCondition());
                        matriz[i][j].setCondition(1);
//                        matriz[i][j+1].setCondition(3);
                        System.out.println(matriz[i][j].getCondition());
//                        break;
                        j=matriz.length;
                        i=matriz.length;
                    }else if(matriz[i][j].getCondition()==1){
//                    System.out.println(x+" "+y);
                        System.out.println(matriz[i][j].getCondition());
                        matriz[i][j].setCondition(0);
//                        matriz[i][j+1].setCondition(3);
                        System.out.println(matriz[i][j].getCondition());
//                        break;
                        j=matriz.length;
                        i=matriz.length;
                    }
                }//else{
//                        System.out.println("sigue ");
//                    }
            }
            
//            System.out.println("no sale ");
//            break;
        }
//        System.out.println(x+" "+y);
    }
    
    public int getX(){
        return map.posX();
    }
    
    public int posY(){
        return map.posY();
    }
}
