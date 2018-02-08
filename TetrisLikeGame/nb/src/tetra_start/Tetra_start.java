package tetra_start;
import com.sun.deploy.ref.AppRef;
import java.io.File;
import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static tetra_start.FXMLDocumentController.Shape;
/*
            * @author vardaru@mef.edu.tr
 */
public class Tetra_start extends Application {
    FXMLDocumentController ctrl;
    private Stage stage;
    static Scene scene;
    public void setStageSize(int column, int row) {
        stage.setWidth((column) * 30);
        stage.setHeight((row + 1) * 30);
    }
    static boolean start = false;
    static boolean Unfairmode = false;
    @Override
    public void start(Stage stage) throws Exception {
        String uriString = new File("sound.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer(new Media(uriString));
        player.play();
        player.setAutoPlay(true);
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument2.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, javafx.scene.paint.Color.BLACK);
        ((FXMLDocumentController) loader.getController()).app = this;
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (FXMLDocumentController.gameoverr == false) {
                    if (event.getCode() == KeyCode.RIGHT) {
                        if (IsItGoRight()) {
                            FXMLDocumentController.point.y++;
                        }
                    }
                    if (event.getCode() == KeyCode.LEFT) {
                        if (IsItGoLeft()) {
                            FXMLDocumentController.point.y--;
                        }
                    }
                    if (event.getCode() == KeyCode.R) {
                        rotate(FXMLDocumentController.Shape.giveMatrix());
                    }
                    if (event.getCode() == KeyCode.TAB) {
                        UnfairMode();
                    }
                    if (event.getCode() == KeyCode.DOWN) {
                        FXMLDocumentController.t = 200;
                    }
                    if (event.getCode() == KeyCode.UP && Unfairmode) {
                        FXMLDocumentController.point.x--;
                    }
                }
            }
            private boolean IsItGoRight() {
                int[][] m = FXMLDocumentController.Shape.giveMatrix().clone();
                if (FXMLDocumentController.point.x + m[0].length == FXMLDocumentController.cloumn - 2) {
                    return false;
                }
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[0].length; j++) {
                        if (Shape.giveMatrix()[i][j] == 1) {
                            if (FXMLDocumentController.point.y + 1 + j <= FXMLDocumentController.cloumn) {
                                if (FXMLDocumentController.map[FXMLDocumentController.point.x + i][FXMLDocumentController.point.y + 1 + j] == 1) {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            private boolean IsItGoLeft() {
                int[][] m = FXMLDocumentController.Shape.giveMatrix().clone();
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[0].length; j++) {
                        if (Shape.giveMatrix()[i][j] == 1) {
                            if (FXMLDocumentController.map[FXMLDocumentController.point.x + i][FXMLDocumentController.point.y - 1 + j] == 1) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            public void rotate(int[][] arr) {
                if (arr.length == 2 && arr[0].length == 2) {
                    return;
                }
                int n = arr.length;
                int m = arr[0].length;
                int[][] output = new int[m][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        output[j][n - 1 - i] = arr[i][j];
                    }
                }
                for (int i = 0; i < output.length; i++) {
                    for (int j = 0; j < output[0].length; j++) {
                        if (output[i][j] == 1) {
                            if (FXMLDocumentController.map[FXMLDocumentController.point.x + i][FXMLDocumentController.point.y + j] == 1) {
                                return;
                            }
                        }
                    }
                }
                FXMLDocumentController.Shape.setMatrix(output);
            }
            private void UnfairMode() {
                Unfairmode = true;

            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Unfairmode) {
                    int i = (int) ((int) event.getX() - 15) / 31;
                    int j = (int) ((int) event.getY() - 15) / 31;
                    System.out.println(event.getButton());

                    if (event.getButton().toString().equals("PRIMARY")) {
                        FXMLDocumentController.map[j][i] = 0;
                    }
                    if (event.getButton().toString().equals("SECONDARY")) {
                        FXMLDocumentController.map[j][i] = 1;
                    }
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.TAB) {
                    Unfairmode = false;
                    scene.setFill(javafx.scene.paint.Color.BLACK);
                }

            }
        });
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
