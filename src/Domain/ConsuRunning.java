package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class ConsuRunning extends Character {

    SynchronizedBuffer sharedLocation;
    private int balls = 0;
    private ProRunning rc;
    private int count = 0;
    private int op = 8;

    public ConsuRunning(int x, int y, int imgNum, SynchronizedBuffer sharedLocation) throws FileNotFoundException {
        super(x, y, imgNum);
        setSprite();
        this.sharedLocation = sharedLocation;

    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        int opcion = 1;
        switch (opcion) {
            case 1:
                for (int i = 1; i < 17; i++) {
                    sprite.add(new Image(new FileInputStream("src/Assets/GokuRunning/Goku_" + i + ".png")));
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
        super.setImage(sprite.get(8));
        int num = 8;
        int value = 0;
        int sum = 0;
        super.setX(950);
        super.setY(600);

        for (int k = 0; k < 7; k++) {
            try {
                super.setX(950);
                super.setY(600);
                super.setImage(sprite.get(8));
                for (int i = 950; i > 550; i = i - 50) {

                    super.setImage(sprite.get(num));

                    num++;

                    super.setX(i);
                    Thread.sleep((int) (Math.random() * 300));
                    //           super.setX(700);
                    //           Thread.sleep(800);
                }
                // super.setImage(sprite.get(9));

                sum += sharedLocation.get();

                //  System.out.print("NP");
                num = 0;
                for (int j = 600; j < 1000; j = j + 50) {

                    super.setImage(sprite.get(num));
                    if (num == 8) {
                        num = 8;
                    } else {
                        num++;
                    }
                    super.setX(j);
                    Thread.sleep((int) (Math.random() * 300));
                    //           super.setX(700);
                    //           Thread.sleep(800);
                }
                balls = 1;
                op++;
            } catch (InterruptedException ex) {
            }

        }
        count = 7;

    }

    public int dragonball() {
        return balls;
    }

    public int shen() {
        return count;
    }

    public int balls2() {
        return op;
    }
}
