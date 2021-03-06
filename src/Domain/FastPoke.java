package Domain;

import Interface.GameMaze;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FastPoke extends Player {

    SynchronizedBuffer sharedLocation;
    private Image image;
    GraphicsContext gc;
    String time;
    GameMaze gM;
    

    public FastPoke(int x, int y, Block[][] matriz, int count, SynchronizedBuffer sharedLocation,String namePlayer,String type) throws FileNotFoundException {
        super(x, y, matriz, count,namePlayer,type);
        setSprite();

        this.sharedLocation = sharedLocation;
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();

        for (int i = 1; i <120; i++) {
            sprite.add(new Image(new FileInputStream("src/assets/Fast/" + i + ".png")));

        }

        super.setSprite(sprite);
    }

    @Override
    public void run() {
          ArrayList<Image> sprite = super.getSprite();
            super.setImage(sprite.get(1));
            int charce=0;
        while (this.noGain) {
            if(pause){

            try {
                int n = move();
                 n=n+count;
                charce++;
               // System.out.println(charce);
                if(charce==10){
                    
                    Thread.sleep(1000);
                    charce=0;
                }
               // System.out.println(n);
                if (n != 0) {
                    super.setImage(sprite.get(n));
                }
                Thread.sleep(200);

            } catch (InterruptedException ex) {
                Logger.getLogger(FastPoke.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FastPoke.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            
        }
        //time=gM.timeReturn();
        sharedLocation.set(namePlayer, type);
        
    }
}
