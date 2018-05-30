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
public class Bloque {
   public int estado;
   private int x;
   private int y;
   private int tamaño;
   
   public Bloque(){
       this.estado=0;
       tamaño=50;
   }
   public Bloque(int n,int x,int y){
       this.estado=n;
       this.tamaño=50;
       this.x=x;
       this.y=y;
   }
   void setEstado(int n){
       this.estado=n;
   }
   public int getEstado(){
       return estado;
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
  
}
