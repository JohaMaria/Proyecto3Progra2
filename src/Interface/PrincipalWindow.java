/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author monge55
 */
public class PrincipalWindow extends Application {

    Pane pane;
    Scene scene;
    private Canvas canvas;
    private Button btnPlayers, btnSelect, btnStart;
    private Label lblCombo, lblFast, lblFurious, lblSmart, lblNumberPlayers, lblNamePlayer;
    private TextField tfNumberPlayers, tfNamePlayer, tfFast,tfFurious, tfSmart;

    private ObservableList<String> listMaze;
    private ComboBox<String> combo;

    private Image imageB;
    private BackgroundSize backS;
    private Background background;
    private BackgroundImage backI;
    
    Image dificultImageP = new Image("/assets/play.png");
    ImageView dificultImageVP = new ImageView(dificultImageP);
    
     Image dificultImageS = new Image("/assets/check.png");
    ImageView dificultImageVS = new ImageView(dificultImageS);
    
     Image dificultImagePl = new Image("/assets/players.png");
    ImageView dificultImageVPl = new ImageView(dificultImagePl);
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Metodo que se llama de primero en FX, se podria considerar el constructor
        primaryStage.setTitle("First Program"); //Pone un titulo a la ventana
        init(primaryStage); //inicializamos los componentes
         primaryStage.setOnCloseRequest(exit);
        primaryStage.show(); //Mostramos la ventana
    }

    public void init(Stage primaryStage) {
//       

        this.pane = new Pane();
        this.scene = new Scene(this.pane, 1400, 700);
//        this.canvas = new Canvas(1200, 700);

        imageB = new Image("assets/fondo2.jpg");
        backS = new BackgroundSize(2000, 1100, true, true, true, true);
        backI = new BackgroundImage(imageB, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backS);
        background = new Background(backI);
        this.pane.setBackground(background);
        primaryStage.setScene(this.scene);
        
        this.lblNumberPlayers = new Label("Number Of Players");
        lblNumberPlayers.setTextFill(Color.LAWNGREEN);

        this.lblNumberPlayers.relocate(100, 100);
        this.pane.getChildren().add(this.lblNumberPlayers);
        
        this.tfNumberPlayers = new TextField();
        tfNumberPlayers.setEditable(true);
        this.tfNumberPlayers.relocate(225, 100);
        this.tfNumberPlayers.setMinSize(25, 10);
        this.pane.getChildren().add(this.tfNumberPlayers);
        
        this.btnPlayers = new Button("Players", dificultImageVPl);
//        btnPlayers.setStyle("-fx-background-color: #00ff00");  button4.setStyle("-fx-text-fill: #0000ff"); letras
        this.btnPlayers.relocate(200, 150);
        this.btnPlayers.setMinSize(50, 40);
        this.pane.getChildren().addAll(this.btnPlayers,dificultImageVPl);
//         this.btnPlayers.setOnAction(buttonAction);
        
        this.lblNamePlayer = new Label("NamePlayer");
        lblNamePlayer.setTextFill(Color.LAWNGREEN);
        this.lblNamePlayer.relocate(100, 300);
        this.pane.getChildren().add(this.lblNamePlayer);
        
        this.tfNamePlayer = new TextField();
        this.tfNamePlayer.relocate(200, 300);
        this.tfNamePlayer.setMinSize(25, 10);
        this.pane.getChildren().add(this.tfNamePlayer);
        
        this.lblFast = new Label("Fast");
        lblFast.setTextFill(Color.LAWNGREEN);
        this.lblFast.relocate(100, 350);
        this.pane.getChildren().add(this.lblFast);
        
        this.tfFast = new TextField();
        this.tfFast.relocate(100,375);
        this.tfFast.setMinSize(10, 10);
        this.pane.getChildren().add(this.tfFast);
        
        this.lblFurious = new Label("Furious");
        lblFurious.setTextFill(Color.LAWNGREEN);
        this.lblFurious.relocate(100, 400);
        this.pane.getChildren().add(this.lblFurious);
        
        this.tfFurious = new TextField();
        this.tfFurious.relocate(100,425);
        this.tfFurious.setMinSize(10, 10);
        this.pane.getChildren().add(this.tfFurious);
        
        this.lblSmart = new Label("Smart");
        lblSmart.setTextFill(Color.LAWNGREEN);
        this.lblSmart.relocate(100, 450);
        this.pane.getChildren().add(this.lblSmart);
        
        this.tfSmart = new TextField();
        this.tfSmart.relocate(100,475);
        this.tfSmart.setMinSize(10, 10);
        this.pane.getChildren().add(this.tfSmart);
        
        this.btnSelect = new Button("",dificultImageVS);
//        btnSelect.setStyle("-fx-background-color: #00ff00");
        this.btnSelect.relocate(300, 415);
        this.btnSelect.setMinSize(75, 35);
        this.pane.getChildren().addAll(this.btnSelect,dificultImageVS);
        

        listMaze = FXCollections.observableArrayList();
        listMaze.addAll("Laberinto1", "Laberinto2", "Laberinto3");
        
        combo = new ComboBox<>(listMaze);
        StackPane pane = new StackPane(combo);
        this.combo.relocate(550, 100);
        this.pane.getChildren().add(combo);
//            primaryStage.show();

        this.lblCombo = new Label("Maze");
        lblCombo.setTextFill(Color.LAWNGREEN);
        this.lblCombo.relocate(500, 100);
        this.pane.getChildren().add(this.lblCombo);

        this.btnStart = new Button("Start", dificultImageVP);
//        btnStart.setStyle("-fx-background-color: #00ff00");
        this.btnStart.relocate(600, 400);
        this.btnStart.setMinSize(100, 50);
        this.pane.getChildren().addAll(this.btnStart, dificultImageVP);
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    GameMaze laberintoGame= new GameMaze();
                    laberintoGame.init(primaryStage);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrincipalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );


        primaryStage.setScene(this.scene);
        primaryStage.setTitle("Juego");
        primaryStage.show();
    }
    
     EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
    
    

}
