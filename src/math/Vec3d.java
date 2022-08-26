package math;

public class Vec3d {
    private double[] vec;

    public Vec3d(double x, double y, double z) {
        this.vec = new double[] {x,y,z};
    }
    public Vec3d(Point3d p) {
        this.vec = new double[] {p.x, p.y, p.z};
    }

    public Vec3d add(Vec3d b) {
        return new Vec3d(getX() + b.getX(), getY() + b.getY(), getZ() + b.getZ());
    }

    public Vec3d sub(Vec3d b) {
        return new Vec3d(getX() - b.getX(), getY() - b.getY(), getZ() - b.getZ());
    }

    public Vec3d mul(Vec3d b) {
        return new Vec3d(getX() * b.getX(), getY() * b.getY(), getZ() * b.getZ());
    }

    public Vec3d mul(double t) {
        return new Vec3d(getX() * t, getY() * t, getZ() * t);
    }

    public Vec3d div(double t) {
        return new Vec3d(getX() / t, getY() / t, getZ() /  t);
    }

    public double len() {
        return Math.sqrt(this.lenSquared());
    }
    public double lenSquared() {
        return getX() * getX() + getY() * getY() + getZ() * getZ() ;
    }

    public void print() {
        System.out.println("[" + getX() + "," + getY() + "," + getZ() + "]");
    }

    public double dot(Vec3d b) {
        return getX() * b.getX() + getY() * b.getY() + getZ() * b.getZ();
    }

    public Vec3d normalize() {
        double len = Math.sqrt(this.dot(this));
        return len == 0 ? new Vec3d(0,0,0) : new Vec3d(getX() / len, getY() / len, getZ() / len);
    }

    public Vec3d cross(Vec3d b) {
        return new Vec3d(getY() * b.getZ() - getZ() * b.getY(),
                getZ() * b.getX() - getX() * b.getZ(),
                getX() * b.getY()  - getY() * b.getX());
    }

    public Vec3d unitVector() {
        return this.div(this.len());
    }

    // Getters

    public double getX() {
        return this.vec[0];
    }
    public double getY() {
        return this.vec[1];
    }
    public double getZ() {
        return this.vec[2];
    }

    public void setVec(double[] vec) {
        this.vec = vec;
    }
    public void setVec(Vec3d v) {
        this.vec = v.getVec();
    }

    public void setVec(double x, double y, double z) {
        this.vec[0] = x;
        this.vec[1] = y;
        this.vec[2] = z;
    }

    public double[] getVec() {
        return vec;
    }
}
