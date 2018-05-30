package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ProRunning extends Character {

    SynchronizedBuffer sharedLocation;
    private Image image;
    GraphicsContext gc;
    private int ball = 1;
    private int balls = 1;

    public ProRunning(int x, int y, int imgNum, SynchronizedBuffer sharedLocation) throws FileNotFoundException {
        super(x, y, imgNum);
        setSprite();

        this.sharedLocation = sharedLocation;
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        int opcion = 1;
        switch (opcion) {
            case 1:
               for (int i = 0; i < 1; i++) {
                    sprite.add(new Image(new FileInputStream("src/assets/carro.png")));
                }
                break;
            case 2:
                System.out.println("Has seleccionado la opcion 2");
                break;
            case 3:
                System.out.println("Has seleccionado la opcion 3");
                break;
            case 4:
//                salir=true;
                break;
            default:
                System.out.println("Solo números entre 1 y 4");
        }

        super.setSprite(sprite);
    }

    @Override
    public void run() {
        ArrayList<Image> sprite = super.getSprite();
        super.setImage(sprite.get(0));
     //   ArrayList<Image> spriteBall = super.getSprite();
        //super.setImage(spriteBall.get(1));
        int num = 0;
        int value = 0;

//        for (int k = 0; k < 6; k++) {
//            try {
//
//                // super.setImage(sprite.get(1));
//                if (ball == 1) {
//                   // super.setX(200);
//                  //  super.setY(500);
//                    for (int i = 0; i < 13; i++) {
//                        super.setImage(sprite.get(num));
//                        num++;
//                        Thread.sleep(305);
//                    }
//                  //  super.setX(400);
//                    //super.setY(600);
//                    for (int i = 200; i < 600; i = i + 50) {
//
//                        super.setImage(sprite.get(num));
//
//                        num++;
//
//                        super.setX(i);
//                        Thread.sleep((int) (Math.random() * 300));
//
//                    }
//
//                    sharedLocation.set(value);
//
//                    for (int j = 550; j > 200; j = j - 50) {
//
//                        super.setImage(sprite.get(num));
//                        if (num == 26) {
//                            num = 28;
//                        } else {
//                            num++;
//                        }
//                        super.setX(j);
//                        Thread.sleep((int) (Math.random() * 300));
//                        //           super.setX(700);
//                        //           Thread.sleep(800);
//                    }
//                    ball++;
//                    balls++;
//                }
//                super.setX(400);
//                super.setY(600);
//                num = 29;
//                super.setImage(sprite.get(num));
//                for (int i = 200; i < 600; i = i + 50) {
//
//                    super.setImage(sprite.get(num));
//
//                    num++;
//
//                    super.setX(i);
//                    Thread.sleep((int) (Math.random() * 300));
//                    //           super.setX(700);
//                    //           Thread.sleep(800);
//                }
//
//                sharedLocation.set(value);
//
////                super.setImage(spriteBall.get(ball));
////                ball++;
//                //System.out.print(ball);
//                //  value++;
//                for (int j = 550; j > 200; j = j - 50) {
//
//                    super.setImage(sprite.get(num));
//                    if (num == 42) {
//                        num = 43;
//                    } else {
//                        num++;
//                    }
//                    super.setX(j);
//                    Thread.sleep((int) (Math.random() * 300));
//                    //           super.setX(700);
//                    //           Thread.sleep(800);
//                }
//                balls++;
//            } catch (InterruptedException ex) {
//            }
//
//        }
//        num = 29;
//        super.setImage(sprite.get(num));
    }

    public int avance() {
        return ball;
    }

    public int avances() {
        return balls;
    }
}
