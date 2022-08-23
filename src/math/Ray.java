package math;

public class Ray {
    private final Point3d origin;
    private final Vec3d dir;
    public Ray(Point3d origin, Vec3d dir) {
        this.origin = origin;
        this.dir = dir;
    }

    public Point3d at(double t) {
        return origin.add(dir.mul(t));
    }

    public Point3d getOrigin() {
        return origin;
    }

    public Vec3d getDir() {
        return dir;
    }

    // Return Vector which represents a color with respective RGB values
    public Vec3d rayColor() {
        double t = hitSphere(new Point3d(0,0,-1), 0.5);
        if (t > 0) {
            // Multiply with Vec3d(1,-1,1) to flip colors horizontally
            Vec3d n = new Vec3d(this.at(t).sub(new Vec3d(0,0,-1))).normalize().mul(new Vec3d(1,-1,1));
            return new Vec3d(n.getX()+1, n.getY()+1, n.getZ()+1).mul(0.5);
        }
        // Multiply with -1 to flip colors horizontally to match implementation of the blog
        Vec3d unitDirection = getDir().unitVector().mul(-1);
        t = 0.5 * (unitDirection.getY() + 1.0);
        return new Vec3d(1,1,1).mul(1.0-t).add(new Vec3d(0.5, 0.7, 1.0).mul(t));
    }
    public double hitSphere(Point3d center, double radius) {
        Vec3d oc = new Vec3d(getOrigin().sub(center));
        double a = getDir().lenSquared();
        double halfB = oc.dot(getDir());
        //double b = 2.0 * oc.dot(getDir());
        double c = oc.lenSquared() - radius * radius;
        double discriminant = halfB*halfB - a*c;
        if (discriminant < 0)
            return -1;
        else
            return (-halfB - Math.sqrt(discriminant)) / (a);
    }
}
