/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Laberinto;
import Domain.Player;
import java.util.Random;

/**
 *
 * @author Jesus
 */
public class Partida {
    Laberinto laberinto;
    Player player;
    int anteriorI;
    int anteriosJ;
    
    public Partida(){
        this.laberinto=new Laberinto();
        this.player=new Player(0,100, 3, 0);
    }
    
    public void movimiento(){
        System.out.println("cada movimiento");
        boolean pase=true;
        boolean a=false;
        boolean b=false;
        boolean c=false;
        boolean d=false;
        boolean sinSalida=true;
        int posI=0;
        int posJ=0;
        int paraDonde;
        for(int i=0;i<laberinto.matriz.length;i++){
            for(int j=0;j<laberinto.matriz[0].length;j++){
                if(laberinto.matriz[i][j].getEstado()==3&&pase){
                    posI=i;
                    posJ=j;
                    System.out.println(posI+" "+posJ);
                    if(j<19&&laberinto.matriz[i][j+1].getEstado()==1&&(anteriorI!=i||anteriosJ!=j+1)){
                        a=true;
//                        anteriorI=i;
//                        anteriosJ=j;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                        pase=false;
                        sinSalida=false;
                    }
                    if(j>0&&laberinto.matriz[i][j-1].getEstado()==1&&(anteriorI!=i||anteriosJ!=j-1)){
                        b=true;
//                        anteriorI=i;
//                        anteriosJ=j;
                        pase=false;
                        sinSalida=false;
//                        paraDonde=2;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                    }
                    if(i<12&&laberinto.matriz[i+1][j].getEstado()==1&&(anteriorI!=i+1||anteriosJ!=j)){
                        c=true;
//                        anteriorI=i;
//                        anteriosJ=j;
                        pase=false;
                        sinSalida=false;
//                        paraDonde=3;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                    }
                    if(i>0&&laberinto.matriz[i-1][j].getEstado()==1&&(anteriorI!=i-1||anteriosJ!=j)){
                        d=true;
//                        anteriorI=i;
//                        anteriosJ=j;
                        pase=false;
                        sinSalida=false;
//                        paraDonde=4;
//                        i=laberinto.matriz.length;
//                        j=laberinto.matriz.length;
                    }
                    if(sinSalida){
                        posI=anteriorI;
                        posJ=anteriosJ;
                        anteriorI=i;
                        anteriosJ=j;
//                        
                        System.out.println("si salida");
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
                        laberinto.matriz[posI][posJ].setEstado(1);
                        laberinto.matriz[posI][posJ+1].setEstado(3);
                        player.setX(laberinto.matriz[posI][posJ+1].getX());
                        anteriorI=posI;
                        anteriosJ=posJ;
                        x=30;
                        break;
                    }else
                        break;
                    
                case 2:
                    if(b){
                        laberinto.matriz[posI][posJ].setEstado(1);
                        laberinto.matriz[posI][posJ-1].setEstado(3);
                        player.setX(laberinto.matriz[posI][posJ-1].getX());
                        anteriorI=posI;
                        anteriosJ=posJ;
                        x=30;
                        break;
                    }else
                        break;
                
                case 3:
                    if(c){
                        laberinto.matriz[posI][posJ].setEstado(1);
                        laberinto.matriz[posI+1][posJ].setEstado(3);
                        player.setY(laberinto.matriz[posI+1][posJ].getY());
                        anteriorI=posI;
                        anteriosJ=posJ;
                        x=30;
                        break;
                    }else
                        break;
//                    
                case 4:
                    if(d){
                        laberinto.matriz[posI][posJ].setEstado(1);
                        laberinto.matriz[posI-1][posJ].setEstado(3);
                        player.setY(laberinto.matriz[posI-1][posJ].getY());
                        anteriorI=posI;
                        anteriosJ=posJ;
                        x=30;
                        break;
                    }else
                        break;
            }
            System.out.println(" "+num);
        }
    }
}
