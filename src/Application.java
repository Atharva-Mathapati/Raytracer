import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Application extends JPanel {
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private BufferedImage canvas;

    public Application(String title) {

        canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPixels();
        g2.drawImage(canvas, null, null);
    }

    private void drawPixels() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                double xCoordinate = (double) x  / WIDTH;
                double yCoordinate = (double) y / HEIGHT;
                // Ray r = new Ray(new Point3d(0,0,0), lowerLeftCorner.add(horizontal.mul(xCoordinate)).add(vertical.mul(yCoordinate)).sub(origin));
                canvas.setRGB(x,y, determineColor(xCoordinate, yCoordinate));
                //canvas.setRGB(x,y, rayColor(r));
            }
        }
    }

    private int determineColor(double x, double y) {
        int r = (int) (x * 255);
        int g = (int) (y * 255);
        return 0xff000000 | (g & 0x000000FF) << 8 | (r & 0x000000FF);
    }

    public static void main(String[] args) {
        new Application("Raytracer");
    }
}
