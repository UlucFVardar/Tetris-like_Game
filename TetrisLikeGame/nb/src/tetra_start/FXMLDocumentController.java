package tetra_start;

import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.net.URL;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * @author vardaru@mef.edu.tr
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TextField ROWN;
    @FXML
    TextField COLUMNN;
    @FXML
    TilePane tilePane;
    @FXML
    Button button;
    @FXML
    ImageView image;
    @FXML
    Text cT;
    @FXML
    Text rT;
    Tetra_start app;
    @FXML
    ImageView gameover;
    static int[][] map;
    static shapes Shape;
    static int row = 0, cloumn = 0;
    static Point point;
    static boolean gameoverr = false;
    Random random = new Random();
    private int y = 0;
    public static int t = 1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Shape = new shapes();
        gameover.setVisible(false);
        tilePane.setVisible(false);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                row = Integer.parseInt(ROWN.getText());
                cloumn = Integer.parseInt(COLUMNN.getText());
                if (row != 0 && cloumn != 0) {
                    app.setStageSize(cloumn + 2, row + 2);
                    Tetra_start.start = true;
                    tilePane.setVisible(true);
                    button.setVisible(false);
                    button.cancelButtonProperty();
                    COLUMNN.setVisible(false);
                    ROWN.setVisible(false);
                    image.setVisible(false);
                    cT.setVisible(false);
                    rT.setVisible(false);
                    point = new Point(0, (int) (cloumn / 2));
                    map = new int[row][cloumn];
                    tilePane.setPrefWidth((cloumn + 1) * 30);
                    tilePane.setPrefHeight((row + 1) * 30);
                    for (int i = 0; i < cloumn * row; i++) {
                        Rectangle rectangle = new Rectangle(30, 30);
                        rectangle.setFill(Color.BLACK);
                        rectangle.setStroke(Color.WHITE);
                        tilePane.getChildren().add(rectangle);
                    }
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(loop, 0, 1);
                }
            }
        });
    }
    TimerTask loop = new TimerTask() {
        @Override
        public void run() {
            if (1 * t % 200 == 0) {
                t = 1;
                for (int i = 0; i < map[0].length; i++) {
                    for (int j = 0; j < map.length; j++) {
                        if (map[j][i] == 0) {
                            getRekt(j, i).setFill(Color.BLACK);
                        } else {
                            getRekt(j, i).setFill(Color.WHITE);
                            getRekt(j, i).setStroke(Color.WHITESMOKE);
                        }
                    }
                }
                IsItGo();
                if (point.y < 0) {
                    point.y = 0;
                }
                for (int i = 0; i < Shape.giveMatrix().length; i++) {
                    for (int j = 0; j < Shape.giveMatrix()[0].length; j++) {
                        if (Shape.giveMatrix()[i][j] == 1) {
                            getRekt(point.x + i, point.y + j).setFill(Shape.c);
                        }
                    }
                }
            }
            t++;
        }
        private void IsItGo() {
            if (Tetra_start.Unfairmode == true) {
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                javafx.scene.paint.Color randomColour = new Color(r, g, b, 1);
                Tetra_start.scene.setFill(randomColour);
                return;
            }
            for (int i = Shape.giveMatrix().length - 1; i >= 0; i--) {
                for (int j = Shape.giveMatrix()[0].length - 1; j >= 0; j--) {
                    if (Shape.giveMatrix()[i][j] == 1) {
                        if (point.x + i == row - 1 || map[point.x + i + 1][point.y + j] == 1) {
                            for (int l = 0; l < Shape.giveMatrix().length; l++) {
                                for (int g = 0; g < Shape.giveMatrix()[0].length; g++) {
                                    if (Shape.giveMatrix()[l][g] == 1) {
                                        map[point.x + l][point.y + g] = 1;
                                        getRekt(point.x + l, point.y + g).setFill(Color.WHITE);
                                        getRekt(point.x + l, point.y + g).setStroke(Color.WHITESMOKE);
                                    }
                                }
                            }
                            //
                            Check();
                            //
                            GameOver();
                            //
                            Shape = new shapes();
                            point = new Point(0, (int) (cloumn / 2));
                            return;
                        }
                    }
                }
            }
            point.x++;
        }
        private void Check() {
            for (int i = map.length - 1; i >= 0; i--) {
                int j = 0;
                for (j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 0) {
                        break;
                    }
                }
                if (j == map[0].length) {
                    for (int q = 0; q < map[0].length; q++) {
                        map[i][q] = 0;
                        getRekt(i, q).setFill(Color.DARKGRAY);
                    }
                    for (int w = i; w > 0; w--) {
                        for (int q = 0; q < map[0].length; q++) {
                            map[w][q] = map[w - 1][q];
                            getRekt(w, q).setFill(getRekt(w - 1, q).getFill());
                        }
                    }
                    i = map.length;
                }
            }
        }
        private void GameOver() {
            for (int t = 0; t < map[0].length; t++) {
                if (map[0][t] == 1) {
                    gameoverr = true;
                    gameover.setFitWidth(cloumn * 30);
                    gameover.setVisible(true);
                    gameover.getEffect();            
                    gameover.setLayoutY(((int) (row * 30 / 2)) - ((int) gameover.getFitHeight() / 2));
                    tilePane.setOpacity(0.20);
                    y += 5;
                    tilePane.setRotate(y);
                }
            }
        }
    };
    public Rectangle getRekt(int i, int j) {
        Rectangle rect = (Rectangle) tilePane.getChildren().get(j + i * (cloumn));
        return rect;
    }
    public double givespace() {
       return tilePane.getHgap();
    }
    private javafx.scene.text.Font Font(String tahoma, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
