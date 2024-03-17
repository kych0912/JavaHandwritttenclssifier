package Util;

import UI.ImageDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RefactoringMatrix {
    private ImageDrawer Drawer;
    private int[][] pixels;
    private double[][] matrix;
    public RefactoringMatrix(ImageDrawer drawer, int width, int height){

        Drawer = drawer;


        int scaleFactor = height / 28;

        BufferedImage img = Drawer.getImg();

        pixels = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int gray = (red + green + blue) / 3;
                pixels[y][x] = gray;
            }
        }


        matrix = new double[28][28];

        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int sum = 0;
                for (int k = 0; k < scaleFactor; k++) {
                    for (int l = 0; l < scaleFactor; l++) {
                        sum += pixels[i * scaleFactor + k][j * scaleFactor + l];
                    }
                }
                matrix[i][j] = 255 - sum / (scaleFactor * scaleFactor);
            }
        }

    }

    public double[][] getMatrix(){
        return matrix;
    }
}
