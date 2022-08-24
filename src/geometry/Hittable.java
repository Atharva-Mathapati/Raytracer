package geometry;

import geometry.HitRecord;
import math.Ray;

public abstract class Hittable {
   public abstract boolean hit(Ray ray, double tMin, double tMax, HitRecord rec);
}
