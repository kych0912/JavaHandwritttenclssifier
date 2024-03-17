package UI;

import java.awt.*;
import javax.swing.*;

import Util.TrainData;

public class JavaGUI extends JFrame{
    private DrawerPanel drawerPanel;
    private TrainData traindata;
    private loadingPanel loadingPanel = new loadingPanel(100,100);
    private TrainPanel trainPanel;
    public JavaGUI(){
        setTitle("Handwritten digit classifier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,350);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        trainPanel  = new TrainPanel(this);

        drawerPanel = new DrawerPanel(this,trainPanel);

        setContentPane(drawerPanel);
        setVisible(true);
    }

    protected void showTrainPanel(){
        setContentPane(trainPanel);
        revalidate();
        repaint();

    }

    protected void showMainPanel(){
        setContentPane(drawerPanel);
        revalidate();
        repaint();

    }


    public static void main(String[] args){
        new JavaGUI();
    }

}
