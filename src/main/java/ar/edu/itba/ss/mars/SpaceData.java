package ar.edu.itba.ss.mars;

public class SpaceData {
    public static final double G = 6.693E-11;
    public static final double d = 1500.0;

    public static class SUN {
        final public static Integer ID = 0;
        final public static Double RADIUS = 695700000.0;
        final public static Double MASS = 1.988E30;
        final public static Double X = 0.0;
        final public static Double Y = 0.0;
        final public static Double VX = 0.0;
        final public static Double VY = 0.0;

        final public static Integer R = 255;
        final public static Integer G = 255;
        final public static Integer B = 0;

    }


    public static class MARS {
        final public static Integer ID = 2;
        final public static Double RADIUS = 3389000.9;
        final public static Double MASS = 6.4185E23;
        final public static Double X = 0.831483493435295E11;
        final public static Double Y = -1.914579540822006E11;
        final public static Double VX = 23000.637912321314047;
        final public static Double VY = 11000.429021426712032;

        final public static Integer R = 255;
        final public static Integer G = 0;
        final public static Integer B = 0;

    }

    public static class EARTH {
        final public static Integer ID = 1;
        final public static Double RADIUS = 6371000.0;
        final public static Double MASS = 5.972E24;
        final public static Double X = 1.391734353396533E11;
        final public static Double Y = -0.571059040560652E11;
        final public static Double VX = 10000.801963811159256;
        final public static Double VY = 27000.565215006898345;

        final public static Integer R = 0;
        final public static Integer G = 0;
        final public static Integer B = 255;

    }

  //TODO: CHANGE_POSITION

    public static class SHIP {
        final public static Integer ID = 3;
        final public static Double RADIUS = 1.0;
        final public static Double MASS = 2E5;
        final public static Double VX = 1000.0;
        final public static Double VY = 1000.0;

        final public static Integer R = 96;
        final public static Integer G = 96;
        final public static Integer B = 96;


        final public static Double X = 0.0;
        final public static Double Y = 0.0;


    }
}
