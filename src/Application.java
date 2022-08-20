import math.Point3d;
import math.Ray;
import math.Vec3d;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Application extends JPanel {
    public static final int WIDTH = 640;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private BufferedImage canvas;
    private double focalLength = 1.0;
    private Point3d origin = new Point3d(0,0,0);
    private double aspectRatio = 16.0 / 9.0;
    private Vec3d horizontal = new Vec3d(aspectRatio* 2.0, 0,0);
    private Vec3d vertical = new Vec3d(0,2.0,0);
    private Vec3d lowerLeftCorner = new Vec3d(origin).sub(horizontal.div(2)).sub(vertical.div(2)).sub(new Vec3d(0,0,focalLength));


    public Application(String title) {

        canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

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
                double xCoordinate = (double) x  / (WIDTH-1);
                double yCoordinate = (double) y / (HEIGHT-1);
                Ray r = new Ray(new Point3d(0,0,0), lowerLeftCorner.add(horizontal.mul(xCoordinate)).add(vertical.mul(yCoordinate)).sub(new Vec3d(origin)));
                // canvas.setRGB(x,y, determineColor(xCoordinate, yCoordinate));
                canvas.setRGB(x,y, colorVecToInt(rayColor(r)));
            }
        }
    }

    private Vec3d rayColor(Ray r) {
        if (hitSphere(new Point3d(0,0,-1), 0.5, r)) {
            return new Vec3d(0,0,1);
        }
        Vec3d unitDirection = r.getDir().div(r.getDir().len());
        double t = 0.5 * (unitDirection.getY() + 1.0);
        return new Vec3d(1,1,1).mul(1.0-t).add(new Vec3d(0.5, 0.7, 1.0).mul(t));
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

    private boolean hitSphere(Point3d center, double radius, Ray r) {
        Vec3d oc = new Vec3d(r.getOrigin().sub(center));
        double a = r.getDir().dot(r.getDir());
        double b = 2.0 * oc.dot(r.getDir());
        double c = oc.dot(oc) - radius * radius;
        double discriminant = b*b - 4*a*c;
        return discriminant > 0;
    }

    public static void main(String[] args) {
        new Application("Raytracer");
    }
}
