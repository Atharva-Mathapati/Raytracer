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

    public Vec3d rayColor() {
        if (hitSphere(new Point3d(0,0,-1), 0.5)) {
            return new Vec3d(0,0,1);
        }
        Vec3d unitDirection = getDir().div(getDir().len());
        double t = 0.5 * (unitDirection.getY() + 1.0);
        return new Vec3d(1,1,1).mul(1.0-t).add(new Vec3d(0.5, 0.7, 1.0).mul(t));
    }
    public boolean hitSphere(Point3d center, double radius) {
        Vec3d oc = new Vec3d(getOrigin().sub(center));
        double a = getDir().dot(getDir());
        double b = 2.0 * oc.dot(getDir());
        double c = oc.dot(oc) - radius * radius;
        double discriminant = b*b - 4*a*c;
        return discriminant > 0;
    }
}
