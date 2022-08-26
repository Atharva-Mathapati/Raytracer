package geometry;

import math.Point3d;
import math.Ray;
import math.Vec3d;

public class Camera {

    private final double aspectRatio;
    double viewportHeight;
    double viewportWidth;
    private final double focalLength;
    private final Point3d origin;
    private final Vec3d horizontal;
    private final Vec3d vertical;
    private final Vec3d lowerLeftCorner;

    public Camera() {
        this.aspectRatio = 16.0 / 9.0;
        this.viewportHeight = 2.0;
        this.viewportWidth = aspectRatio * viewportHeight;
        this.focalLength = 1.0;
        this.origin = new Point3d(0,0,0);
        this.horizontal = new Vec3d(viewportWidth , 0,0);
        this.vertical = new Vec3d(0,viewportHeight,0);
        this.lowerLeftCorner = new Vec3d(origin).sub(horizontal.div(2)).sub(vertical.div(2)).sub(new Vec3d(0,0,focalLength));


    }

    public Ray getRay(double xCoordinate, double yCoordinate) {
        // Multiply with Vec3d(1,-1,1) to flip colors horizontally
        return new Ray(origin, lowerLeftCorner.add(horizontal.mul(xCoordinate)).add(vertical.mul(yCoordinate)).sub(new Vec3d(origin)).mul(new Vec3d(1,-1,1)));

    }

}
