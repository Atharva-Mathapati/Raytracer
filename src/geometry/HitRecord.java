package geometry;

import math.Point3d;
import math.Ray;
import math.Vec3d;

public class HitRecord {
    public Point3d p;
    public Vec3d normal;
    public double t;
    public boolean frontFace;
    public void setFaceNormal(Ray r, Vec3d outwardNormal) {
        this.frontFace = r.getDir().dot(outwardNormal) < 0;
        this.normal = frontFace ? outwardNormal : outwardNormal.mul(-1);
    }
}
