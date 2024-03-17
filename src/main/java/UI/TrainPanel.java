package UI;

import Util.TrainData;
import Util.TrainSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TrainPanel extends JPanel {
    private TrainData traindata = new TrainData();
    private JPanel trainNumberFieldWrapper;
    private TrainNumberField TNF = new TrainNumberField(this);
    private loadingPanel loading = new loadingPanel(100,100);
    private JPanel dataPanel;
    private int num;
    public TrainPanel(JavaGUI main){
        setLayout(new BorderLayout());

        trainNumberFieldWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        trainNumberFieldWrapper.add(TNF);
        add(trainNumberFieldWrapper,"North");

        JButton mainBtn = new JButton("main");
        mainBtn.addActionListener(e->main.showMainPanel());

        add(mainBtn,"South");

        dataPanel = new JPanel();

        add(dataPanel,"Center");
    }

    public void TrainDataSet(JTextField tf){
        String input = tf.getText();
        if(!isInteger(input)){
            return;
        }

        try {
            int value = Integer.parseInt(input);
            if (value < 100 || value > 10000) {
                JOptionPane.showMessageDialog(null, "Only 100 to 10000", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not Validation", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        traindata = new TrainData(Integer.parseInt(input));

    }

    public TrainData getTrainData(){
        return traindata;
    }

    public void ShowTrainedImage(){
        for(int i=0;i<10;i++){
            dataPanel.add(new ShowMatrixToCanvas(traindata.getTrainedImage()[i]));
            revalidate();
            repaint();
        }
    }
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
