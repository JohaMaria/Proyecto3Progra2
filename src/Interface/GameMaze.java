package Interface;

import Domain.Block;
import Domain.Maze;
import Domain.Player;
import Domain.FastPoke;
import Domain.FuriousPoke;
import Domain.Item;
import Domain.Item1;
import Domain.SmartPoke;

import Domain.SynchronizedBuffer;
import Domain.Winner;
//import static Interface.Chronometer.timeline;
import Utility.Variables;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.swing.ImageIcon;

public class GameMaze extends Application implements Runnable {

    Pane pane;
    Scene scene;
    private Canvas canvas;
    Thread thread;
    Maze maze;
    
    Runnable tabla = new Runnable() {
        @Override
        public void run() {
            while (true) {
                uptabe();
            }
        }
    };
    
    private Player player;
    private FastPoke playerFast[];
    private SmartPoke playerSmart[];
    private FuriousPoke playerFurious[];
    private Button btnPlay, btnPause, btnStop, btnStart, btnMaze1, btnMaze2, btnMaze3;
    Player player2, player3;
    int quantityFast, quantitySmart, quantityFurious;
    int move = 1;
    int change = 0;
    int playerNum, fastPokeQ, furiousPokeQ, smartPokeQ;
    String namePlayer;
    private FastPoke rc;
    private SynchronizedBuffer sharedLocation;
        Item1[] item;
    boolean aux = true;
    private Button btnPlayers, btnSelect;
    private Label lblCombo, lblFast, lblFurious, lblSmart, lblNumberPlayers, lblNamePlayer;
    public TextField tfNumberPlayers, tfNamePlayer, tfFast, tfFurious, tfSmart;
    int imageNum = 0;
    public TableView<Winner> table;
    private ObservableList<String> listMaze;
    private ComboBox<String> combo;
    boolean boolstar=true;
    int items=0;
    int numMap=1;

    Image dificultImage = new Image("/assets/pause.png");
    ImageView dificultImageV = new ImageView(dificultImage);

    Image dificultImagePo = new Image("/assets/play.png");
    ImageView dificultImageVPs = new ImageView(dificultImagePo);

    Image dificultImageSo = new Image("/assets/start.png");
    ImageView dificultImageVSs = new ImageView(dificultImageSo);

    Image dificultImageSp = new Image("/assets/stop.jpg");
    ImageView dificultImageVSp = new ImageView(dificultImageSp);
    Image dificultImageP = new Image("/assets/play.png");
    ImageView dificultImageVP = new ImageView(dificultImageP);

    Image dificultImageS = new Image("/assets/check.png");
    ImageView dificultImageVS = new ImageView(dificultImageS);

    Image dificultImagePl = new Image("/assets/players.png");
    ImageView dificultImageVPl = new ImageView(dificultImagePl);

    //  private ObservableList<Winner> winnerT = FXCollections.observableArrayList();
    private Image imageB;
    private BackgroundSize backS;
    private Background background;
    private BackgroundImage backI;
    //cronometro
    public static Timeline timeline;
    private final Label timerLabel = new Label();

    private final DoubleProperty timeSeconds = new SimpleDoubleProperty();
    private Duration time = Duration.ZERO;
    public static ArrayList<Double> records = new ArrayList<Double>();
    Player player1;
    private Button btnChrono, btnLlb;
    private Winner winner;
//    private PrincipalWindow pW;
    public static String timer = "00.00";
    private Runnable cronometer = new Runnable() {
        @Override
        public void run() {
            Integer minutos = 0, segundos = 0, milesimas = 0;
            //min es minutos, seg es segundos y mil es milesimas de segundo
            String min = "", seg = "", mil = "";
            try {
                //Mientras cronometroActivo sea verdadero entonces seguira
                //aumentando el tiempo
                while (true) {
                    Thread.sleep(4);
                    //Incrementamos 4 milesimas de segundo
                    milesimas += 4;

                    //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                    //y las milesimas de segundo de nuevo a 0
                    if (milesimas == 1000) {
                        milesimas = 0;
                        segundos += 1;
                        //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                        //y los segundos vuelven a 0
                        if (segundos == 60) {
                            segundos = 0;
                            minutos++;
                        }
                    }

                    //Esto solamente es estetica para que siempre este en formato
                    //00:00:000
                    if (minutos < 10) {
                        min = "0" + minutos;
                    } else {
                        min = minutos.toString();
                    }
                    if (segundos < 10) {
                        seg = "0" + segundos;
                    } else {
                        seg = segundos.toString();
                    }

                    if (milesimas < 10) {
                        mil = "00" + milesimas;
                    } else if (milesimas < 100) {
                        mil = "0" + milesimas;
                    } else {
                        mil = milesimas.toString();
                    }

                    //Colocamos en la etiqueta la informacion
                    //timerLabel.setText( min + ":" + seg + ":" + mil );
                    timer = min + ":" + seg + ":" + mil;
                    //  System.err.println(timer);
                }
            } catch (Exception e) {
            }
            //Cuando se reincie se coloca nuevamente en 00:00:000
            //timerLabel.setText( "00:00:000" );
        }
    };
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

            this.btnStart = new Button("", dificultImageVSs);
            this.btnStart.relocate(1050, 100);
            this.pane.getChildren().addAll(btnStart, dificultImageVSs);
            this.lblNumberPlayers = new Label("Number Of Players");
            lblNumberPlayers.setTextFill(Color.BLACK);

            this.lblNumberPlayers.relocate(20, 600);
            this.pane.getChildren().add(this.lblNumberPlayers);

            this.tfNumberPlayers = new TextField();
            tfNumberPlayers.setEditable(true);
            this.tfNumberPlayers.relocate(125, 600);
            this.tfNumberPlayers.setMinSize(25, 10);
            this.pane.getChildren().add(this.tfNumberPlayers);

            this.btnPlayers = new Button("Players", dificultImageVPl);
            this.btnPlayers.relocate(125, 630);
            this.btnPlayers.setMinSize(50, 40);
            this.btnPlayers.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    playerNum = Integer.parseInt(tfNumberPlayers.getText());
                    tfNumberPlayers.setEditable(false);
                    // System.out.println(playerNum);
                }
            });
            this.pane.getChildren().addAll(this.btnPlayers, dificultImageVPl);
//         this.btnPlayers.setOnAction(buttonAction);

            this.lblNamePlayer = new Label("NamePlayer");
            lblNamePlayer.setTextFill(Color.WHITE);
            this.lblNamePlayer.relocate(600, 575);
            this.pane.getChildren().add(this.lblNamePlayer);

            this.tfNamePlayer = new TextField();
            this.tfNamePlayer.relocate(675, 575);
            this.tfNamePlayer.setMinSize(25, 10);
            this.pane.getChildren().add(this.tfNamePlayer);

            this.lblFast = new Label("Fast:");
            lblFast.setTextFill(Color.WHITE);
            this.lblFast.relocate(400, 575);
            this.pane.getChildren().add(this.lblFast);

            this.tfFast = new TextField();
            this.tfFast.relocate(435, 575);
            this.tfFast.setMinSize(10, 10);
            this.tfFast.setText("0");
     this.pane.getChildren().add(this.tfFast);

            this.lblFurious = new Label("Furious:");
            lblFurious.setTextFill(Color.WHITE);
            this.lblFurious.relocate(390, 625);
             this.pane.getChildren().add(this.lblFurious);

            this.tfFurious = new TextField();
            this.tfFurious.relocate(435, 625);
            this.tfFurious.setMinSize(10, 10);
            this.tfFurious.setText("0");
            this.pane.getChildren().add(this.tfFurious);

            this.lblSmart = new Label("Smart:");
            lblSmart.setTextFill(Color.WHITE);
            this.lblSmart.relocate(400, 675);
            this.pane.getChildren().add(this.lblSmart);

            this.tfSmart = new TextField();
            this.tfSmart.relocate(435, 675);
            this.tfSmart.setMinSize(10, 10);
            this.tfSmart.setText("0");
            this.pane.getChildren().add(this.tfSmart);

            this.btnSelect = new Button("", dificultImageVS);
            this.btnSelect.relocate(620, 620);
            this.btnSelect.setMinSize(75, 35);
            this.btnSelect.setOnAction(new EventHandler<ActionEvent>() {
               

                @Override
                public void handle(ActionEvent event) {

                    if (playerNum != 1) {
                        fastPokeQ += Integer.parseInt(tfFast.getText());
                        furiousPokeQ += Integer.parseInt(tfFurious.getText());
                        smartPokeQ += Integer.parseInt(tfSmart.getText());
                        //   System.out.println(fastPokeQ);
                        playerNum--;

                    } else {
                        tfFast.setText("0");
                        tfSmart.setText("0");
                        tfFurious.setText("0");
                        tfFast.setEditable(false);
                        tfFurious.setEditable(false);
                        tfSmart.setEditable(false);

                    }

                }
            });
            // sharedLocationPoke.setPoke(fastPokeQ, furiousPokeQ, smartPokeQ);

           this.pane.getChildren().addAll(this.btnSelect, dificultImageVS);
            listMaze = FXCollections.observableArrayList();
            listMaze.addAll("Laberinto1", "Laberinto2", "Laberinto3");

            combo = new ComboBox<>(listMaze);
            StackPane pane = new StackPane(combo);
            this.combo.relocate(550, 100);
            // this.pane.getChildren().add(combo);
//            primaryStage.show();

            this.lblCombo = new Label("Maze");
            lblCombo.setTextFill(Color.LAWNGREEN);
            this.lblCombo.relocate(500, 100);
            //this.pane.getChildren().add(this.lblCombo);

            this.btnPlay = new Button("", dificultImageVPs);
            this.btnPlay.relocate(1050, 150);
            this.pane.getChildren().addAll(btnPlay, dificultImageVPs);

            this.btnPause = new Button("", dificultImageV);
            this.btnPause.relocate(950, 150);
            this.pane.getChildren().addAll(btnPause, dificultImageV);

            this.btnStop = new Button("", dificultImageVSp);
            this.btnStop.relocate(1150, 150);
            this.pane.getChildren().addAll(btnStop, dificultImageVSp);

            this.btnChrono = new Button("");
            this.btnChrono.relocate(1150, 50);
            this.btnChrono.setMinSize(150, 75);
            this.pane.getChildren().addAll(btnChrono);

            this.btnLlb = new Button("Chronometer");
            this.btnLlb.relocate(1150, 25);
            this.btnLlb.setMinSize(150, 25);
            this.pane.getChildren().add(btnLlb);
            
            this.btnMaze1 = new Button("Maze 1");
            this.btnMaze1.relocate(850, 520);
            this.btnMaze1.setMinSize(80, 30);
            this.pane.getChildren().addAll(btnMaze1);
            
            this.btnMaze2 = new Button("Maze 2");
            this.btnMaze2.relocate(850, 560);
            this.btnMaze2.setMinSize(80, 30);
            this.pane.getChildren().addAll(btnMaze2);
            
            this.btnMaze3 = new Button("Maze 3");
            this.btnMaze3.relocate(850, 600);
            this.btnMaze3.setMinSize(80, 30);
            this.pane.getChildren().addAll(btnMaze3);

            initTable();
            uptabe();
            this.pane.getChildren().addAll(table);

            this.pane.getChildren().add(this.canvas);

            primaryStage.setScene(this.scene);

            maze = new Maze(numMap);
            imageNum = 0;
//           quantityFast=sharedLocationPoke.getFast();

            this.playerFast = new FastPoke[0];
            for (int i = 0; i < playerFast.length; i++) {
                if (imageNum == 0) {
                    this.playerFast[i] = new FastPoke(0, 80, maze.matriz, 1, sharedLocation, "Juan", "Fast");

                } else {
                    this.playerFast[i] = new FastPoke(0, 80, maze.matriz, imageNum * 8, sharedLocation, "Pedro", "Fast");

                }
                imageNum++;
                if (imageNum == 15) {
                    imageNum = 0;
                }

            }
            imageNum = 0;
            this.playerSmart = new SmartPoke[4];
            for (int i = 0; i < playerSmart.length; i++) {
                if (imageNum == 0) {
                    this.playerSmart[i] = new SmartPoke(0, 80, maze.matriz, 1, sharedLocation, "", "Smart");
                } else {
                    this.playerSmart[i] = new SmartPoke(0, 80, maze.matriz, imageNum * 8, sharedLocation, "", "Smart");
                }
                imageNum++;
                if (imageNum == 15) {
                    imageNum = 0;
                }
//                this.playerSmart[i].start();
            }
            imageNum = 0;
            this.playerFurious = new FuriousPoke[0];
            for (int i = 0; i < playerFurious.length; i++) {
                if (imageNum == 0) {
                    this.playerFurious[i] = new FuriousPoke(0, 80, maze.matriz, 1, sharedLocation, "Carlos", "Furious");
                } else {
                    this.playerFurious[i] = new FuriousPoke(0, 80, maze.matriz, imageNum * 8, sharedLocation, "Pedro", "Furious");
                }
                imageNum++;
                if (imageNum == 15) {
                    imageNum = 0;
                }
//                this.playerFurious[i].start();
            }
            imageNum = 0;
            this.item=new Item1[20];

            this.btnPause.setOnAction(new EventHandler<ActionEvent>() {

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
            this.btnPlay.setOnAction(new EventHandler<ActionEvent>() {

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
            this.btnStart.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if(boolstar){
                        new Thread(cronometer).start();
                        chronometer();
                        for (int i = 0; i < playerFast.length; i++) {

                            playerFast[i].start();
                        }
                        for (int i = 0; i < playerSmart.length; i++) {

                            playerSmart[i].start();
                        }
                        for (int i = 0; i < playerFurious.length; i++) {

                            playerFurious[i].start();
                        }
                        boolstar=false;
                    }

                }
            });
            this.btnStop.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    for (int i = 0; i < playerFast.length; i++) {

                        playerFast[i].noGain = false;
                    }
                    for (int i = 0; i < playerSmart.length; i++) {
                        playerSmart[i].noGain = false;
                    }
                    for (int i = 0; i < playerFurious.length; i++) {
                        playerFurious[i].noGain = false;
                    }
                }
            });
            
            this.btnMaze1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    numMap=1;
                    Maze aux=new Maze(numMap);
                    maze=aux;
                    for(int i=0;i<playerFast.length;i++){
                        playerFast[i].setMatriz(maze.matriz);
//                        playerFast[i].setX();
                    }
                    for(int i=0;i<playerSmart.length;i++){
                        playerSmart[i].setMatriz(maze.matriz);
                    }
                    for(int i=0;i<playerFurious.length;i++){
                        playerFurious[i].setMatriz(maze.matriz);
                    }
                    
                }
            });
            
            this.btnMaze2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    numMap=2;
                    Maze aux=new Maze(numMap);
                    maze=aux;
                    for(int i=0;i<playerFast.length;i++){
                        playerFast[i].setMatriz(maze.matriz);
                        
                    }
                    for(int i=0;i<playerSmart.length;i++){
                        playerSmart[i].setMatriz(maze.matriz);
                    }
                    for(int i=0;i<playerFurious.length;i++){
                        playerFurious[i].setMatriz(maze.matriz);
                    }
                    
                }
            });
            
            this.btnMaze3.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    numMap=3;
                    Maze aux=new Maze(numMap);
                    maze=aux;
                    for(int i=0;i<playerFast.length;i++){
                        playerFast[i].setMatriz(maze.matriz);
                        playerFast[i].setY(160);
                    }
                    for(int i=0;i<playerSmart.length;i++){
                        playerSmart[i].setMatriz(maze.matriz);
                    }
                    for(int i=0;i<playerFurious.length;i++){
                        playerFurious[i].setMatriz(maze.matriz);
                    }
                    
                }
            });
            this.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        System.out.println("se presiono x: " + event.getSceneX() + "se presiono y: " + event.getSceneY());
                        maze.mouse((int) event.getSceneX(), (int) event.getSceneY());
                    }
                    else if(event.getButton()==MouseButton.SECONDARY){
                        if(items<20){
                            Item1 aux=new Item1((int) event.getSceneX()/40, (int) event.getSceneY()/40, maze);
                            item[items]=aux;
                            aux.start();
                            items++;                    
                        }
                    }
                }

            });

            this.thread = new Thread(this);
            this.thread.start();
            new Thread(tabla).start();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameMaze.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void draw(GraphicsContext gc) {

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1200, 700);
        maze.piaintMatriz(gc);
        for (int i = 0; i < playerFast.length; i++) {
            this.playerFast[i].draw(gc);
            

        }
        for (int i = 0; i < playerSmart.length; i++) {
            this.playerSmart[i].draw(gc);
            for(int j=0;j<items;j++){
                    if(playerSmart[i].getJ()==item[j].getPosX()&&playerSmart[i].getI()==item[j].getPosY()){
                        playerSmart[i].setSleep(400);
                        item[j].setPosX(5000);
                        item[j].murio=false;
                    }
                }

        }
        for (int i = 0; i < playerFurious.length; i++) {
            this.playerFurious[i].draw(gc);
            for(int j=0;j<items;j++){
                    if(playerFurious[i].getJ()==item[j].getPosX()&&playerFurious[i].getI()==item[j].getPosY()){
                        item[j].setPosX(5000);
                        item[j].murio=false;
                    }
                }
        }
        
         for(int i=0;i<items;i++){
            item[i].draw(gc);
        }
        
        
        try {
            Thread.sleep(150);

        } catch (InterruptedException ex) {
            Logger.getLogger(GameMaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chronometer() {
//        Image clockImage = new Image("/Assets/clock.png");
//        ImageView clockIV = new ImageView(clockImage);

        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.BLACK);
        timerLabel.setStyle("-fx-font-size: 5em;");
        if (timeline != null) {
        } else {
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(200),//100
                            new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            Duration duration = ((KeyFrame) t.getSource()).getTime();
                            time = time.add(duration);
                            timeSeconds.set(time.toSeconds());

                        }
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);//INDEFINITE
            timeline.play();
        }
        VBox vb = new VBox(10);
        vb.getChildren().addAll(timerLabel);//, splitTimerLabel);
        vb.relocate(1160, 50);
        pane.getChildren().addAll(vb);//VB
//        pane.getChildren().addAll(button);
    }

   public void initTable() {

        table = new TableView<Winner>();
        TableColumn ColumnName = new TableColumn("Name");
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("namePlayer"));
        TableColumn ColumnType = new TableColumn("Type");
        ColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn ColumnTime = new TableColumn("Time");
        ColumnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        table.getColumns().addAll(ColumnName, ColumnType, ColumnTime);
        this.table.relocate(950, 300);
    }

    public void uptabe() {
        // this.table.getItems().clear();
        this.table.setItems(this.sharedLocation.get());
    }

    public String timeReturn() {
        return timer;
    }
    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
    
    public void setMap(int num){
         numMap=num;
    }
    
}
