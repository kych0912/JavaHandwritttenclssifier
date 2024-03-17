package Util;

public class TrainSet {
    private int number;
    private int[][] matrix;
    public TrainSet(int number,int[][] matrix){
        this.matrix = matrix;
        this.number = number;
    }
    public int[][] getMatrix(){
        return matrix;
    }

    public int getNumber(){
        return number;
    }
}
