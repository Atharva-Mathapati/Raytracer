import geometry.Camera;
import geometry.HittableList;
import geometry.Sphere;
import math.Point3d;
import math.Ray;
import math.Util;
import math.Vec3d;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Application extends JPanel {
    public static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int samplesPerPixel = 100;
    // Camera
    private final Camera camera= new Camera();
    // World
    private final HittableList world = new HittableList();

    // GUI
    private final BufferedImage canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


    public Application(String title) {

        // add Spheres
        world.add(new Sphere(new Point3d(0,-100.5,-1), 100));
        world.add(new Sphere(new Point3d(0,0,-1), 0.5));

        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);

        // Basic Movement for experimental purposes, move this elsewhere eventually
        /* frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               return;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                focalLength -= 0.05;
                lowerLeftCorner = new Vec3d(origin).sub(horizontal.div(2)).sub(vertical.div(2)).sub(new Vec3d(0,0,focalLength));
                repaint();
                System.out.println("done");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                return;
            }
        });
         */
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPixels();
        g2.drawImage(canvas, null, null);
    }

    private void drawPixels() {
        double u;
        double v;
        Vec3d color = new Vec3d(0,0,0);
        for (int y = HEIGHT-1; y >= 0; --y) {
            for (int x = 0; x < WIDTH; ++x) {
                for (int i = 0; i < samplesPerPixel; i++) {
                    u = (x + Util.rand()) / (WIDTH-1);
                    v = (y  + Util.rand()) / (HEIGHT-1);
                    Ray r = camera.getRay(u,v);
                    color.setVec(color.add(r.rayColor(world)));
                }
                // canvas.setRGB(x,y, determineColor(xCoordinate, yCoordinate));
                canvas.setRGB(x, y, colorVecToInt(color));
                color.setVec(0,0,0);
            }
        }
    }

    private static int colorVecToInt(Vec3d v) {

        double scale = 1.0 / samplesPerPixel;
        double r = v.getX() * scale;
        double g = v.getY() * scale;
        double b = v.getZ() * scale;
        int rgb = (int) (Util.clamp(r, 0.0, 0.999) * 255) ; // r
        rgb = (rgb << 8) + (int) (Util.clamp(g, 0.0, 0.999) * 255);
        rgb = (rgb << 8) + (int) (Util.clamp(b, 0.0, 0.999)  * 255);
        return rgb;
    }

//    private int determineColor(double x, double y) {
//        // if (hitSphere(new Point3d(0,0,-1), 0.5, ))
//        int r = (int) (x * 255);
//        int g = (int) (y * 255);
//        return 0xff000000 | (g & 0x000000FF) << 8 | (r & 0x000000FF);
//    }

    public static void main(String[] args) {
        Application app = new Application("Raytracer");
    }
}
