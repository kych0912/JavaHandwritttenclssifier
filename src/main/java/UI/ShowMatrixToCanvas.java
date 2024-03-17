package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ShowMatrixToCanvas extends JPanel {
    private int[][] matrix;

    //Drawer 280x280 to 28x28
    ShowMatrixToCanvas(int[][] matrix){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(28, 28));
        this.matrix = matrix;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        for (int y = 0; y < width; y++) {
//            for (int x = 0; x < height; x++) {
//                if (pixels[y][x] == 0) {
//                    g.fillRect(x, y, 10, 10);
//                }
//            }
//        }
        for (int y = 0; y < 28; y++) {
            for (int x = 0; x < 28; x++) {
                if (matrix[y][x] >100) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }

}
