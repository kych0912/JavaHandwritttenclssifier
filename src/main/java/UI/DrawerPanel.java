package UI;
import Util.ClassifierHandwritten;
import Util.RefactoringMatrix;
import Util.TrainData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
public class DrawerPanel extends JPanel {
    private JavaGUI main;
    private JPanel DrawerWrapper;
    private TrainPanel panel;
    private ClassifierHandwritten classifier;
    private RefactoringMatrix refactoring;
    private TrainData data;
    private ImageDrawer drawer;
    private JPanel BtnPanel;
    private JButton trainBtn;
    private JButton clearButton;
    private JButton predictionButton;
    private JLabel label;
    public DrawerPanel(JavaGUI main, TrainPanel data) {
        this.main = main;
        this.panel = data;

        this.data = panel.getTrainData();

        setLayout(new BorderLayout());
        DrawerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        drawer = new ImageDrawer(280,280);

        addBtn();

        DrawerWrapper.add(drawer);
        DrawerWrapper.add(BtnPanel);

        add(DrawerWrapper, BorderLayout.CENTER);
        add(addPrediction(),BorderLayout.SOUTH);
    }

    private void addBtn(){
        BtnPanel = new JPanel();
        BtnPanel.setLayout(new GridLayout(3,1,10,10));

        trainBtn = new JButton("train");
        trainBtn.setPreferredSize(new Dimension(100, 30));
        trainBtn.addActionListener(e->main.showTrainPanel());

        clearButton = new JButton("clear");
        clearButton.setPreferredSize(new Dimension(100, 30));
        clearButton.addActionListener(e -> drawer.clear());

        predictionButton = new JButton("prediction");
        predictionButton.setPreferredSize(new Dimension(100, 30));
        predictionButton.addActionListener(e->{
            this.data = panel.getTrainData();

            classifier = new ClassifierHandwritten(data);
            refactoring = new RefactoringMatrix(drawer,280,280);

            int cl = classifier.Classifier(refactoring.getMatrix());
            label.setText("Your digit is " + cl);
        });

        BtnPanel.add(trainBtn);
        BtnPanel.add(clearButton);
        BtnPanel.add(predictionButton);
    }

    private JPanel addPrediction(){
        JPanel prediction = new JPanel();
        label = new JLabel("Your digit is");

        prediction.add(label);

        return prediction;
    }

}
