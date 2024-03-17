package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.*;
public class ImageDrawer extends JPanel {
    private BufferedImage img;
    private Graphics2D imgG2;
    private DrawListener listener = null;

    private int prevMouseX = -1;
    private int prevMouseY = -1;

    public ImageDrawer(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        imgG2 = img.createGraphics();
        setBackground(Color.WHITE);
        imgG2.fillRect(0, 0, width, height);
        imgG2.setColor(Color.BLACK);
        imgG2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                prevMouseX = e.getX();
                prevMouseY = e.getY();
                if(listener != null) {
                    listener.onDrawStart();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(listener != null) {
                    listener.onDrawEnd();
                }
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }

    public void clear(){
        imgG2.setColor(Color.WHITE);
        imgG2.fillRect(0, 0, img.getWidth(), img.getHeight());
        imgG2.setColor(Color.BLACK);
        repaint();
    }

    public BufferedImage getImg(){
        return img;
    }

    private void handleMouseDrag(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        imgG2.drawLine(prevMouseX, prevMouseY, mouseX, mouseY);
        prevMouseX = mouseX;
        prevMouseY = mouseY;
        repaint();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(Color.GRAY.getRGB()));
        g2.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public interface DrawListener {
        void onDrawEnd();
        void onDrawStart();
    }
}
