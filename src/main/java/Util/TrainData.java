package Util;
import java.io.*;
import java.sql.Array;
import java.util.*;

public class TrainData {
    private int[] numCount= new int[10];
    private List<List<String>> csvList;
    private TrainSet[] Set;
    private int[][][] SumImage;
    private int[][][] AverageImage;
    private int N;
    private int m = 28;

    public TrainData(){
        AverageImage = new int[10][m][m];
        for(int i=0;i<10;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(AverageImage[i][j],0);
            }
        }
    }
    public TrainData(int num){
        N = num;

        readCSV();
        CountNum();
        TrainingProcess();
    }

    public void readCSV(){
        csvList = new ArrayList<List<String>>();
        String csvFile = "/src/main/resources/numbers.csv";

        File csv = new File(csvFile);

        BufferedReader br = null;

        String line = "";

        try{
            br = new BufferedReader(new FileReader(csv));
            while((line = br.readLine()) != null){
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(",");
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }

            DataPreprocessing(csvList);

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(br!=null){
                    br.close();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void DataPreprocessing(List<List<String>> data){
        Set = new TrainSet[N];

        for (int n = 0; n < N; n++) {
            List<String> rowData = data.get(n);
            int[][] Matrix = new int[28][28];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    Matrix[i][j]= Integer.parseInt(rowData.get(1 + i * m + j));
                }
            }

            Set[n] = new TrainSet(Integer.parseInt(rowData.get(0)),Matrix);

        }

    }

    private void CountNum(){
        Arrays.fill(numCount,0);
        for(int i=0;i<N;i++){
            numCount[Set[i].getNumber()] +=1;
        }

    }


    private void TrainingProcess(){
        SumImage = new int[10][m][m];
        AverageImage = new int[10][m][m];

        for(int i=0;i<N;i++){
            SumArray(SumImage[Set[i].getNumber()],Set[i].getMatrix());
        }

        for(int i=0;i<10;i++){
            AverageImage[i] = divideArray(SumImage[i],numCount[i]);
        }


    }

    private void SumArray(int[][] result,int[][] arr){
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                result[i][j] += arr[i][j];
            }
        }
    }

    private int[][] divideArray(int[][] arr,int n){
        int[][] result = new int[28][28];

        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                result[i][j] = arr[i][j] / n;
            }
        }

        return result;
    }

    public int[][][] getTrainedImage(){
        return AverageImage;
    }
}
