package geometry;

import geometry.HitRecord;
import geometry.Hittable;
import math.Point3d;
import math.Ray;
import math.Vec3d;

public class Sphere extends Hittable {

    private final double radius;
    private final Point3d center;

    public Sphere(Point3d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        Vec3d oc = new Vec3d(r.getOrigin().sub(center));
        double a = r.getDir().lenSquared();
        double halfB = oc.dot(r.getDir());
        double c = oc.lenSquared() - radius * radius;
        double discriminant = halfB * halfB - a * c;
        if (discriminant < 0)
            return false;
        double sqrtd = Math.sqrt(discriminant);

        double root = (-halfB - sqrtd) / a;
        if (root < tMin || root > tMax) {
            root = (-halfB - sqrtd) / a;
            if (root < tMin || root < tMax) {
                return false;
            }
        }

        rec.t = root;
        rec.p = r.at(rec.t);
        Vec3d outwardNormal = new Vec3d(rec.p.sub(center).div(radius));
        rec.setFaceNormal(r, outwardNormal);
        return true;
    }
}
