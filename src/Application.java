import geometry.Hittable;
import geometry.HittableList;
import geometry.Sphere;
import math.Point3d;
import math.Ray;
import math.Vec3d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Application extends JPanel {
    public static final int WIDTH = 640;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private final double aspectRatio = 16.0 / 9.0;

    // Camera
    double viewportHeight = 2.0;
    double viewportWidth = aspectRatio * viewportHeight;
    private double focalLength = 1.0;
    private final Point3d origin = new Point3d(0,0,0);
    private final Vec3d horizontal = new Vec3d(viewportWidth , 0,0);
    private final Vec3d vertical = new Vec3d(0,viewportHeight,0);
    private Vec3d lowerLeftCorner = new Vec3d(origin).sub(horizontal.div(2)).sub(vertical.div(2)).sub(new Vec3d(0,0,focalLength));

    // World
    private HittableList world = new HittableList();

    // GUI
    private final BufferedImage canvas;


    public Application(String title) {
        canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

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
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                // use coordinates to position ray accordingly
                double xCoordinate = (double) x  / (WIDTH-1);
                double yCoordinate = (double) y / (HEIGHT-1);

                // Multiply with Vec3d(1,-1,1) to flip colors horizontally
                Ray r = new Ray(new Point3d(0,0,0), lowerLeftCorner.add(horizontal.mul(xCoordinate)).add(vertical.mul(yCoordinate)).sub(new Vec3d(origin)).mul(new Vec3d(1,-1,1)));
                // canvas.setRGB(x,y, determineColor(xCoordinate, yCoordinate));
                canvas.setRGB(x, y, colorVecToInt(r.rayColor(world)));
            }
        }
    }

    private static int colorVecToInt(Vec3d b) {
        int rgb = (int) (b.getX() * 255) ; // r
        rgb = (rgb << 8) + ((int) (b.getY() * 255));
        rgb = (rgb << 8) + ((int) (b.getZ() * 255));
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
