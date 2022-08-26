package math;

import java.util.Random;

public class Util {
    public static double pi = 3.1415926535897932385;
    public static double rand() {
        Random r  = new Random();
        return r.nextDouble();
    }
    public static double rand(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static double clamp(double x, double min, double max) {
        if (x < min) return min;
        if (x > max) return max;

        return x;
    }
}
