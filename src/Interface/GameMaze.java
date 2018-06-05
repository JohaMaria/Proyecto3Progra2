package Interface;

import Domain.Block;
import Domain.Maze;
import Domain.Player;
import Domain.FastPoke;
import Domain.FuriousPoke;
import Domain.Item;
import Domain.SmartPoke;

import Domain.SynchronizedBuffer;
import Utility.Variables;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.ImageIcon;

public class GameMaze extends Application implements Runnable {

    Pane pane;
    Scene scene;
    private Canvas canvas;
    Thread thread;
    Maze maze;
    private Player player;
    private FastPoke playerFast[];
    private SmartPoke playerSmart[];
    private FuriousPoke playerFurious[];
    private Button btnStart, btnPause, btnStop;
    Player player2, player3;
    int quantity = 2;
    int move = 1;
    int change = 0;
    private FastPoke rc;
    private SynchronizedBuffer sharedLocation;
    Item item;
    boolean aux = true;
    private Button btnPause1;
    private Button btnStart1;
    private Button btnrun;

    Image dificultImage = new Image("/assets/pause.png");
    ImageView dificultImageV = new ImageView(dificultImage);

    Image dificultImageP = new Image("/assets/play.png");
    ImageView dificultImageVP = new ImageView(dificultImageP);

    Image dificultImageS = new Image("/assets/start.png");
    ImageView dificultImageVS = new ImageView(dificultImageS);

    private Image imageB;
    private BackgroundSize backS;
    private Background background;
    private BackgroundImage backI;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Graphics and Threads");
        init(primaryStage);

        primaryStage.setOnCloseRequest(exit);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    @Override
    public void run() {

        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;

        while (true) {
            try {
                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 1000000;
                Thread.sleep(wait);
                GraphicsContext gc = this.canvas.getGraphicsContext2D();
                draw(gc);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void init(Stage primaryStage) throws InterruptedException {
        try {
            this.pane = new Pane();
            this.scene = new Scene(this.pane, 1400, 700);
            this.canvas = new Canvas(840, 550);
            this.sharedLocation = new SynchronizedBuffer();

            imageB = new Image("assets/fondo8.jpg");
            backS = new BackgroundSize(1400, 700, true, true, true, true);
            backI = new BackgroundImage(imageB, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backS);
            background = new Background(backI);
            this.pane.setBackground(background);
            primaryStage.setScene(this.scene);

            this.btnPause1 = new Button("", dificultImageV);
            this.btnPause1.relocate(1100, 235);
            this.pane.getChildren().addAll(btnPause1, dificultImageV);

            this.btnStart = new Button("", dificultImageVP);
            this.btnStart.relocate(1100, 150);
            this.pane.getChildren().addAll(btnStart, dificultImageVP);

            this.btnrun = new Button("", dificultImageVS);
            this.btnrun.relocate(1100, 100);
            this.pane.getChildren().addAll(btnrun, dificultImageVS);

//        this.thread = new Thread(this);
//        this.thread.start();
//            this.btnStart.relocate(400, 1100);
//        this.btnStart.setMinSize(75, 35);
//        this.pane.getChildren().add(this.btnStart);
            this.pane.getChildren().add(this.canvas);

            primaryStage.setScene(this.scene);

            maze = new Maze();
            this.playerFast = new FastPoke[quantity];
            for (int i = 0; i < playerFast.length; i++) {
                this.playerFast[i] = new FastPoke(0, 80, maze.matriz, 1, sharedLocation);
//                this.playerFast[i].start();
            }
            this.playerSmart = new SmartPoke[quantity];
            for (int i = 0; i < playerSmart.length; i++) {
                this.playerSmart[i] = new SmartPoke(0, 80, maze.matriz, 1, sharedLocation);
//                this.playerSmart[i].start();
            }
            this.playerFurious = new FuriousPoke[quantity];
            for (int i = 0; i < playerFurious.length; i++) {
                this.playerFurious[i] = new FuriousPoke(0, 80, maze.matriz, 1, sharedLocation);
//                this.playerFurious[i].start();
            }
            this.item = new Item(10, 4);

//            this.rc = new ProRunning(0, 100, maze.matriz, 1, sharedLocation);
//            this.rc.start();
//        player=new Player(0,100,maze.matriz,1);
//        player2=new Player(350, 0,maze.matriz,1);
//        player3=new Player(0,100,maze.matriz,1);
//        player2.cambiarImagen();
            this.btnPause1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    for (int i = 0; i < playerFast.length; i++) {

                        playerFast[i].pause = false;
                    }
                    for (int i = 0; i < playerSmart.length; i++) {
                        playerSmart[i].pause = false;
                    }
                    for (int i = 0; i < playerFurious.length; i++) {
                        playerFurious[i].pause = false;
                    }
                }
            });
            this.btnStart.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    for (int i = 0; i < playerFast.length; i++) {

                        playerFast[i].pause = true;
                    }
                    for (int i = 0; i < playerSmart.length; i++) {
                        playerSmart[i].pause = true;
                    }
                    for (int i = 0; i < playerFurious.length; i++) {
                        playerFurious[i].pause = true;
//                    System.out.println("segundo boton");
                    }
                }
            });
            this.btnrun.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    for (int i = 0; i < playerFast.length; i++) {

                        playerFast[i].start();
                    }
                    for (int i = 0; i < playerSmart.length; i++) {

                        playerSmart[i].start();
                    }
                    for (int i = 0; i < playerFurious.length; i++) {

                        playerFurious[i].start();
                    }
                }
            });
//        partida=new Partida();
//        System.out.println(maze.matriz[3][2].getEstado());
//        m=maze.getMatriz();
//        maze.llennarMatriz();
//        this.thread = new Thread(this);
//        this.thread.start();
            this.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        System.out.println("se presiono x: " + event.getSceneX() + "se presiono y: " + event.getSceneY());
                        maze.mouse((int) event.getSceneX(), (int) event.getSceneY());
                    }
                }

            });

            this.thread = new Thread(this);
            this.thread.start();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameMaze.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void draw(GraphicsContext gc) {
//        partida.maze.piaintMatriz(gc);
//        partida.player.draw(gc);
//        play.move();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1200, 700);
        maze.piaintMatriz(gc);
        for (int i = 0; i < playerFast.length; i++) {
            this.playerFast[i].draw(gc);
            if (playerFurious[i].getJ() == item.getPosX() && playerFurious[i].getI() == item.getPosY()) {
                item.setPosX(5000);
            }
        }
        for (int i = 0; i < playerSmart.length; i++) {
            this.playerSmart[i].draw(gc);
            if (playerSmart[i].getJ() == item.getPosX() && playerSmart[i].getI() == item.getPosY()) {
                this.playerSmart[i].setSleep(300);
                item.setPosX(5000);
            }
        }
        for (int i = 0; i < playerFurious.length; i++) {
            this.playerFurious[i].draw(gc);
        }
        this.item.draw(gc);

        if (item.getPosX() == 10) {
            aux = true;
        } else if (item.getPosX() == 16) {
            aux = false;
        }
        if (aux) {
            item.setPosX(item.getPosX() + 1);
        } else {
            item.setPosX(item.getPosX() - 1);
        }

//        gc.drawImage(this.rc.getImage(), this.rc.getX(), this.rc.getY());
//       // for(int i=0;i<this.players.length;i++){
//            
//           //this.players[i].draw(gc); 
////           player=players[3];
//           player.draw(gc);
//            player.move();
//     //   }
//       // player.draw(gc);
//        player2.draw(gc);
//        player3.draw(gc);
////        player.start();
//        
//     //   player.move();
//        player2.move();
//        player3.move();
        try {
            Thread.sleep(150);

        } catch (InterruptedException ex) {
            Logger.getLogger(GameMaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}
