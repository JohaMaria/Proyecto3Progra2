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
public class Mapa {
   public Bloque[][] matriz=new Bloque[13][20];
    private int[][] numeros={{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                            {3,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0},
                            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                            {0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                            {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                            {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public Mapa(){
        for(int i=0;i<matriz.length;i++){
            for (int j = 0; j<matriz[0].length; j++) {
                matriz[i][j]=new Bloque(numeros[i][j],j*50,i*50);
            }
        }
    }
}
