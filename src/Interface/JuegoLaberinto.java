package Interface;

import Domain.Bloque;
import Domain.ConsuRunning;
import Domain.Laberinto;
import Domain.Player;
import Domain.ProRunning;

import Domain.SynchronizedBuffer;
import Utility.Variables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.ImageIcon;

public class JuegoLaberinto extends Application implements Runnable {

    Pane pane;
    Scene scene;
    private Canvas canvas;
    Thread thread;
    Laberinto laberinto;
    private Bloque[][] m = new Bloque[10][10];
    private Player player;
    private ProRunning rc;
    private SynchronizedBuffer sharedLocation;

    @Override
    public void start(Stage primaryStage) {
        init(primaryStage);
        primaryStage.setOnCloseRequest(exit);
//        laberinto=new Laberinto();
//        laberinto.llennarMatriz();
    }

    @Override
    public void run() {
        while (true) {
            GraphicsContext gc = this.canvas.getGraphicsContext2D();
            draw(gc);
        }
    }

    public void init(Stage primaryStage) {
        try {
            this.pane = new Pane();
            this.scene = new Scene(this.pane, 1200, 700);
            this.canvas = new Canvas(1200, 700);

//        this.thread = new Thread(this);
//        this.thread.start();
            this.pane.getChildren().add(this.canvas);

            primaryStage.setScene(this.scene);

            primaryStage.setTitle("Juego");

//        primaryStage.setOnCloseRequest(exit);
            primaryStage.show();

            laberinto = new Laberinto();

//player=new Player(0,100*i, 3, 0);
            this.rc = new ProRunning(-50, 250, 0, sharedLocation);
            this.rc.start();
//        System.out.println(laberinto.matriz[3][2].getEstado());
//        m=laberinto.getMatriz();
//        laberinto.llennarMatriz();

//        this.thread = new Thread(this);
//        this.thread.start();
            this.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        System.out.println("se presiono x: " + event.getSceneX() + "se presiono y: " + event.getSceneY());
                        laberinto.mouse((int) event.getSceneX(), (int) event.getSceneY());
                    }
                }

            });

            this.thread = new Thread(this);
            this.thread.start();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JuegoLaberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void draw(GraphicsContext gc) {
        //  Laberinto laberinto=new Laberinto();

        boolean paso = true;
        int variable = 0;
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
         gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
        for (int i = 0; i < laberinto.matriz.length; i++) {
            for (int j = 0; j < laberinto.matriz[0].length; j++) {
                if (laberinto.matriz[i][j].getEstado() == 0) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(50 * j, 50 * i, 50, 50);
                } else if (laberinto.matriz[i][j].getEstado() == 1) {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(50 * j, 50 * i, 50, 50);
                }
                gc.drawImage(this.rc.getImage(), this.rc.getX(), this.rc.getY());//                if(laberinto.matriz[i][j].getEstado()==3&&paso){
//                    gc.drawImage(player.image, player.x, player.y, 40, 40);
//                    if(laberinto.matriz[i][j+1].getEstado()==1){//derecha
//                        player.setX(laberinto.matriz[i][j+1].getX());
//                        player.setY(laberinto.matriz[i][j+1].getY());
//                        laberinto.matriz[i][j].setEstado(1);
//                        laberinto.matriz[i][j+1].setEstado(3);
//                        paso=false;
//                        
//                    }
//                    else if(laberinto.matriz[i+1][j].getEstado()==1){//abajo
//                        player.setX(laberinto.matriz[i+1][j].getX());
//                        player.setY(laberinto.matriz[i+1][j].getY());
//                       laberinto.matriz[i][j].setEstado(1);
//                        laberinto.matriz[i+1][j].setEstado(3);
//                        paso=false;
//                       
//                    }
//                    else if(laberinto.matriz[i][j-1].getEstado()==1){//izq
//                        player.setX(laberinto.matriz[i][j-1].getX());
//                        player.setY(laberinto.matriz[i][j-1].getY());
//                     laberinto.matriz[i][j-1].setEstado(1);
//                       variable=1;
//                        laberinto.matriz[i][j].setEstado(3);
//                        paso=false;
//                    }
//                    else if(laberinto.matriz[i-1][j].getEstado()==1){//arriba
//                        player.setX(laberinto.matriz[i-1][j].getX());
//                        player.setY(laberinto.matriz[i-1][j].getY());
//                        //laberinto.matriz[i][j].setEstado(1);
//                        laberinto.matriz[i-1][j].setEstado(3);
//                        System.out.println("NP");
//                        paso=false;
//                    }
//                }    
            }
        }
        try {
            Thread.sleep(300);
//        gc.drawImage(player.image, player.x, player.y, 40, 40);
//        gc.setFill(Color.BLUE);
//        gc.fillOval(40, 50, 20, 20);
        } catch (InterruptedException ex) {
            Logger.getLogger(JuegoLaberinto.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Thread.sleep(600);
        } catch (InterruptedException ex) {
            Logger.getLogger(JuegoLaberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}

//public class Window extends Application implements Runnable {
//
//    private Thread thread;
//    private Scene scene;
//    private Pane pane;
//    private Canvas canvas;
//    private Image image, imageShen;
//    private Image images, image2;
//    private SynchronizedBuffer sharedLocation;
////    private StandingCharacter sc;
//    private ProRunning rc;
//    private ConsuRunning jc;
//    private int x = 1;
//    private int y = 625;
//    private Character character;
//    private ImageIcon imagen1;
//    private int op = 0;
//    private int op2 = 0;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Graphics and Threads");
//        initComponents(primaryStage);
//
//        primaryStage.setOnCloseRequest(exit);
//        primaryStage.show();
//    }
//
//    @Override
//    public void run() {
//
//        long start;
//        long elapsed;
//        long wait;
//        int fps = 30;
//        long time = 1000 / fps;
//
//        while (true) {
//            try {
//                start = System.nanoTime();
//                elapsed = System.nanoTime() - start;
//                wait = time - elapsed / 1000000;
//                Thread.sleep(wait);
//                GraphicsContext gc = this.canvas.getGraphicsContext2D();
//                draw(gc);
//            } catch (InterruptedException ex) {
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private void initComponents(Stage primaryStage) {
//        try {
//            this.pane = new Pane();
//            this.scene = new Scene(this.pane, Variables.WIDTH, Variables.HEIGHT);
//            this.canvas = new Canvas(Variables.WIDTH, Variables.HEIGHT);
//            this.image = new Image(new FileInputStream("src/Assets/background.png"));
//            this.images = new Image(new FileInputStream("src/Assets/DragonBalls/ball_" + x + ".png"));
//            this.imageShen = new Image(new FileInputStream("src/Assets/DragonBalls/ball_8.png"));
//            this.pane.getChildren().add(this.canvas);
//            this.sharedLocation = new SynchronizedBuffer();
//
//            primaryStage.setScene(this.scene);
//
//            //Inicializamos cada hilo (personaje) y lo iniciamos
//            //SynchronizedBuffer sharedLocations = new SynchronizedBuffer();
////            this.sc = new StandingCharacter(600, 50, 0, sharedLocation);
////            this.sc.start();
//            for(int i=0;i<2;i++){
//            this.rc = new ProRunning(-50, 250*i, 0, sharedLocation);
//            this.rc.start();
//            }
//            this.jc = new ConsuRunning(450, 370, 2, sharedLocation);
//            this.jc.start();
//           
//
//            // Consumer consumer = new Consumer(450,370,3,sharedLocation);
//            //   consumer.start();
//            this.thread = new Thread(this);
//            this.thread.start();
//        } catch (FileNotFoundException ex) {
//        } catch (BufferOverflowException ex) {
//        }
//    }
//
//    private void draw(GraphicsContext gc) throws FileNotFoundException {
//        //  ImageIcon imagenAux=listaImagenes.get( 0 );
//        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
//        gc.drawImage(this.image, 0, 0);
//        // gc.drawImage(this.sc.getImage(), this.sc.getX(), this.sc.getY());
//        gc.drawImage(this.rc.getImage(), this.rc.getX(), this.rc.getY());
//        gc.drawImage(this.jc.getImage(), this.jc.getX(), this.jc.getY());
//        if (sharedLocation.ball() == 1) {
//            
//            op2 = rc.avances();
//            if(op2==8){
//                op2=0;
//            }
//            this.images = new Image(new FileInputStream("src/Assets/DragonBalls/ball_" + op2 + ".png"));
//
//            gc.drawImage(images, 575, 625);
//        }
//        if (jc.dragonball() == 1) {
//            op = jc.balls2();
//            this.image2 = new Image(new FileInputStream("src/Assets/DragonBalls/ball_" + op + ".png"));
//
//            gc.drawImage(image2, 1000, y);
//
//        }
//        if (jc.shen() == 7) {
//            gc.drawImage(imageShen, 900, 300);
//        }
//        //x=x+25;
//    }
//
//    public void setSpriteBalls() throws FileNotFoundException {
//        ArrayList<ImageIcon> listaImagenes = new ArrayList<ImageIcon>();
//        for (int j = 1; j < 9; j++) {
//            this.imagen1 = new ImageIcon("src/Assets/DragonBalls/ball_" + j + ".png");
//        }
//        for (int i = 1; i < 9; i++) {
//            listaImagenes.add(imagen1);
//
//        }
//    }
//
//    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
//        @Override
//        public void handle(WindowEvent event) {
//            System.exit(0);
//        }
//    };
//}
