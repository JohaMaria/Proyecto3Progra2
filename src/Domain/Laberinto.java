/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Jesus
 */
public class Laberinto {
    public Bloque[][] matriz;
    private Bloque pared;
    private Bloque camino;
    Mapa mapa;
    
    public Laberinto(){
//        this.matriz=new Bloque[10][10];
        mapa=new Mapa();
        this.matriz=mapa.matriz;
//        pared=new Bloque(0);
//        camino=new Bloque(1);
        
//        for(int i=0;i<matriz.length;i++){
//            for(int j=0;j<matriz[0].length;j++){
//                matriz[i][j]=pared;
//                matriz[i][j].setX(j*50);
//                matriz[i][j].setY(i*50);
//            }
//        }
//        for(int m=0;m<matriz.length;m++){
//            matriz[3][m]=camino;
//            matriz[3][m].setX(m*50);
//            matriz[3][m].setY(3*50);
//        }
    }
    
//    public void llennarMatriz(){
//        for(int i=0;i<matriz.length;i++){
//            for(int j=0;j<matriz[0].length;j++){
//                if(i==3){
//                    matriz[i][j]=new Bloque(1);
//                    matriz[i][j].setX(j*50);
//                    matriz[i][j].setY(i*50);
//                }
//                else{
//                    matriz[i][j]=new Bloque(0);
//                    matriz[i][j].setX(j*50);
//                    matriz[i][j].setY(i*50);
//                }
////                System.out.println(matriz[i][j].getX());
//            }
//        }
////        for(int m=0;m<matriz.length;m++){
////            matriz[3][m]=camino;
////            matriz[3][m].setX(m*50);
////            matriz[3][m].setY(3*50);
////        }
//    }           
    public Bloque[][] getMatriz(){
        return matriz;
    }
    
    public void mouse(int x, int y){
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                if ((x>=j*50&&x<=(j+1)*50)&&(y>=i*50&&y<=(i+1)*50)) {
                    if(matriz[i][j].getEstado()==0){
                        System.out.println(matriz[i][j].getEstado());
                        matriz[i][j].setEstado(1);
//                        matriz[i][j+1].setEstado(3);
                        System.out.println(matriz[i][j].getEstado());
//                        break;
                        j=matriz.length;
                        i=matriz.length;
                    }else if(matriz[i][j].getEstado()==1){
//                    System.out.println(x+" "+y);
                        System.out.println(matriz[i][j].getEstado());
                        matriz[i][j].setEstado(0);
//                        matriz[i][j+1].setEstado(3);
                        System.out.println(matriz[i][j].getEstado());
//                        break;
                        j=matriz.length;
                        i=matriz.length;
                    }
                }//else{
//                        System.out.println("sigue ");
//                    }
            }
            
            System.out.println("no sale ");
//            break;
        }
//        System.out.println(x+" "+y);
    }
}
