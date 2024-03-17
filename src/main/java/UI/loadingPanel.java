package UI;

import javax.swing.*;
import java.awt.*;
public class loadingPanel extends JPanel {
    {
        setOpaque(false);
    }
    private int width;
    private int height;
    public loadingPanel(int width,int height) {
        this.width = width;
        this.height = height;
        add(loading());
    }

    private JPanel loading(){
        JPanel panel = new JPanel();
        BoxLayout layoutMgr = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layoutMgr);

        ImageIcon imageIcon = new ImageIcon("resources/ZKZg.gif");

        Image img = imageIcon.getImage();
        Image changeImg = img.getScaledInstance(width,height, Image.SCALE_DEFAULT);
        ImageIcon newimageIcon = new ImageIcon(changeImg);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(newimageIcon);
        newimageIcon.setImageObserver(iconLabel);

        JLabel label = new JLabel("Loading...");
        panel.add(iconLabel);
        panel.add(label);
        return panel;
    }
}
