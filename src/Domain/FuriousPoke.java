/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Arturo
 */
public class FuriousPoke  extends Player{
    SynchronizedBuffer sharedLocation;
    private Image image;
    GraphicsContext gc;
    public FuriousPoke(int x, int y, Block[][] matriz, int count, SynchronizedBuffer sharedLocation) throws FileNotFoundException {
        super(x, y, matriz, count);
        setSprite();

        this.sharedLocation = sharedLocation;
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();

        for (int i = 1; i < 121; i++) {
            sprite.add(new Image(new FileInputStream("src/assets/Furious/" + i + ".png")));

        }

        super.setSprite(sprite);
    }

    @Override
    public void run() {
          ArrayList<Image> sprite = super.getSprite();
            super.setImage(sprite.get(1));
            int charce=900;
        while (this.noGain) {
          
            if(pause){
            try {
                int n = move();
                n=n+count;
               
               
               
               // System.out.println(n);
                if (n != 0) {
                    super.setImage(sprite.get(n));
                }
                Thread.sleep(charce);

            } catch (InterruptedException ex) {
                Logger.getLogger(FastPoke.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
                try {
                    Thread.sleep(900);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FuriousPoke.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}