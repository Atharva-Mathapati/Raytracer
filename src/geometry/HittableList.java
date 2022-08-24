package geometry;

import math.Ray;

import java.util.ArrayList;

public class HittableList extends Hittable {
    public ArrayList<Hittable> objects = new ArrayList<>();

    public void add(Hittable o){
        objects.add(o);
    }

    @Override
    public boolean hit(Ray ray, double tMin, double tMax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = tMax;

        for (Hittable object : objects) {
            if (object.hit(ray, tMin, closestSoFar, tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.t;
                rec.normal = tempRec.normal;
                rec.p = tempRec.p;
                rec.t = tempRec.t;
                rec.frontFace = tempRec.frontFace;
            }
        }

        return hitAnything;
    }
}
