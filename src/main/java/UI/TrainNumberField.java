package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;
public class TrainNumberField extends JPanel{
    private loadingPanel loading = new loadingPanel(100,100);
    public TrainNumberField(TrainPanel trainPanel){
        JTextField trainNum = new JTextField();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        trainNum.setPreferredSize(new Dimension(300,30));

        JButton trainBtn = new JButton("Train");
        trainBtn.addActionListener(e->{
            this.add(loading,"Center");
            revalidate();

            trainPanel.TrainDataSet(trainNum);
            this.remove(loading);

            trainPanel.ShowTrainedImage();
        });

        JLabel label = new JLabel("Train DataSet Num");

        add(label);
        add(trainNum);
        add(trainBtn);
    }
}
