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
public class Block {
   private int condition;
   private int x;
   private int y;
   private int size;
   
   public Block(){
       this.condition=0;
       size=50;
   }
   public Block(int n,int x,int y){
       this.condition=n;
       this.size=50;
       this.x=x;
       this.y=y;
   }
   public void setCondition(int n){
       this.condition=n;
   }
  public int getCondition(){
       return condition;
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
