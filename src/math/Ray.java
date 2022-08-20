package math;

public class Ray {
    private Point3d origin;
    private Vec3d dir;
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
}
