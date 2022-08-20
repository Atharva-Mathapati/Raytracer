package math;

public class Point3d {
    public double x,y,z;

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d add(Vec3d v) {
        return new Point3d(v.getX() + x, v.getY() + y, v.getZ() + z);
    }

    public Point3d sub(Point3d b) {
        return new Point3d(x - b.x , y - b.y,  z - b.z);
    }
}
