package tetra_start;
import java.util.Random;
import javafx.scene.paint.Color;
/**
 *  @author vardaru@mef.edu.tr
 */
public class shapes {
    int r;
    String type;
    Color c;
    private int[][] I;
    private int[][] T;
    private int[][] L;
    private int[][] J;
    private int[][] Z;
    private int[][] G;
    private int[][] O;
    shapes() {
        Random rnd = new Random();
        r = rnd.nextInt(7) + 1;
        if (r == 1) {
            c = Color.YELLOW;
            type = "I";
            I = new int[4][1];
            I[0][0] = 1;
            I[2][0] = 1;
            I[1][0] = 1;
            I[3][0] = 1;
        }
        if (r == 2) {
            c = Color.YELLOWGREEN;
            type = "T";
            T = new int[2][3];
            T[0][2] = 1;
            T[0][0] = 1;
            T[0][1] = 1;
            T[1][1] = 1;
        }
        if (r == 3) {
            c = Color.AQUA;
            type = "L";
            L = new int[3][2];
            L[0][0] = 1;
            L[0][1] = 1;
            L[1][0] = 1;
            L[2][0] = 1;
        }
        if (r == 4) {
            c = Color.ORANGE;
            type = "J";
            J = new int[3][2];
            J[0][0] = 1;
            J[0][1] = 1;
            J[1][1] = 1;
            J[2][1] = 1;
        }
        if (r == 5) {
            type = "Z";
            c = Color.RED;
            Z = new int[3][2];
            Z[0][1] = 1;
            Z[1][0] = 1;
            Z[1][1] = 1;
            Z[2][0] = 1;
        }
        if (r == 6) {
            type = "G";
            c = Color.BLUEVIOLET;
            G = new int[3][2];
            G[0][0] = 1;
            G[1][0] = 1;
            G[1][1] = 1;
            G[2][1] = 1;
        }
        if (r == 7) {
            type = "O";
            c = Color.PINK;
            O = new int[2][2];
            O[0][0]=1;
            O[0][1]=1;
            O[1][0]=1;
            O[1][1]=1;
        }
    }  
     public int[][] giveMatrix() {
        if (r == 1) {
            return I;
        }
        if (r == 2) {
            return T;
        }
        if (r == 3) {
            return L;
        }
        if (r == 4) {
            return J;
        }
        if (r == 5) {
            return Z;
        }
        if (r == 6) {
            return G;
        }
         if (r == 7) {
            return O;
        }
        return null;

    }
    public void setMatrix(int[][] a) {
        if (r == 1) {
            I = a;
        }
        if (r == 2) {
            T = a;
        }
        if (r == 3) {
            L = a;
        }
        if (r == 4) {
            J = a;
        }
        if (r == 5) {
            Z = a;
        }
        if (r == 6) {
            G = a;
        }
    }
}
