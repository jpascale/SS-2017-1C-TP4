package ar.edu.itba.ss.mars;

public class SpaceData {
    static final double G = 6.693E-11;
    static final double d = 1500000.0; // 1500 km

    static class SUN {
        final static Integer ID = 0;
        final static Double RADIUS = 695700000.0;
        final static Double MASS = 1.988E30;
        final static Double X = 0.0;
        final static Double Y = 0.0;
        final static Double VX = 0.0;
        final static Double VY = 0.0;

        final static Integer R = 255;
        final static Integer G = 255;
        final static Integer B = 0;

    }

    static class MARS {
        final static Integer ID = 2;
        final static Double RADIUS = 3389900.0;
        final static Double MASS = 6.4185E23;
        final static Double X = 0.831483493435295E11;
        final static Double Y = -1.914579540822006E11;
        final static Double VX = 23637.912321314047;
        final static Double VY = 11429.021426712032;

        final static Integer R = 255;
        final static Integer G = 0;
        final static Integer B = 0;

    }

    static class EARTH {
        final static Integer ID = 1;
        final static Double RADIUS = 6371000.0;
        final static Double MASS = 5.972E24;
        final static Double X = 1.391734353396533E11;
        final static Double Y = -0.571059040560652E11;
        final static Double VX = 10801.963811159256;
        final static Double VY = 27565.215006898345;

        final static Integer R = 0;
        final static Integer G = 0;
        final static Integer B = 255;

    }

    static class SHIP {
        final static Integer ID = 3;
        final static Double RADIUS = 1000.0;
        final static Double MASS = 2E5;

        final static Integer R = 255;
        final static Integer G = 255;
        final static Integer B = 255;

    }
}
