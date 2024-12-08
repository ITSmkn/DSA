package postfixprefix;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


    public class Plotter extends JFrame {
    ArrayList<ArrayList<Double>> coordinates;
    private double zoomFactor = 20.0;

    public Plotter(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
        setTitle("Expression Graph");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.cyan);

        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                renderPlot(g);
            }
        };

        getContentPane().add(canvas);
        setVisible(true);

        canvas.setBackground(Color.cyan);

        canvas.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int rotation = e.getWheelRotation();
                if (rotation < 0) {
                    zoomFactor *= 1.1; 
                } else {
                    zoomFactor /= 1.1; 
                }
                repaint();
            }
        });
    }

    private void renderPlot(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, centerY, getWidth(), centerY);
        g2d.drawLine(centerX, 0, centerX, getHeight());

        g2d.setColor(Color.darkGray);
        for (int i = 0; i < coordinates.size(); i++) {
            ArrayList<Double> point = coordinates.get(i);

            int pixelX = centerX + (int) (point.get(0) * zoomFactor);
            int pixelY = centerY - (int) (point.get(1) * zoomFactor);

            g2d.fillOval(pixelX - 3, pixelY - 3, 6, 6);

            if (i > 0) {
                ArrayList<Double> prevPoint = coordinates.get(i - 1);
                int prevPixelX = centerX + (int) (prevPoint.get(0) * zoomFactor);
                int prevPixelY = centerY - (int) (prevPoint.get(1) * zoomFactor);

                g2d.draw(new Line2D.Double(prevPixelX, prevPixelY, pixelX, pixelY));
            }
        }
    }
}
