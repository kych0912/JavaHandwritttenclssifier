package Util;

public class ClassifierHandwritten {
    private TrainData data;
    private int[][][] trained;
    public ClassifierHandwritten(TrainData traindata){
        data = traindata;
        this.trained = data.getTrainedImage();

    }

    public int Classifier(double[][] x){
        double[] xDistance = new double[10];

        for (int i = 0; i < 10; i++) {
            double[][] difference = new double[x.length][x[0].length];

            for (int j = 0; j < x.length; j++) {
                for (int k = 0; k < x[0].length; k++) {
                    difference[j][k] = x[j][k] - trained[i][j][k];
                }
            }
            xDistance[i] = calculateNorm(difference);
        }

        return getIndexOfMinValue(xDistance);
    }

    private int getIndexOfMinValue(double[] array) {
        int minIndex = 0;
        double minValue = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static double calculateNorm(double[][] matrix) {
        double norm = 0.0;
        for (double[] row : matrix) {
            for (double value : row) {
                norm += Math.pow(value, 2);
            }
        }
        return Math.sqrt(norm);
    }
}
