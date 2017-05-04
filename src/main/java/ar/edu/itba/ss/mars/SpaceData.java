package ar.edu.itba.ss.mars;

public class SpaceData {
    static final double G = 6.693E-11;
    static final double d = 1500000.0; // 1500 km

    static class SUN {
        final static Integer ID = 0;
        final static Double RADIUS = 6.957E8;
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
        final static Double RADIUS = 3.3899E6;
        final static Double MASS = 6.4185E23;
        final static Double X = 8.31483493435295E10;
        final static Double Y = -1.914579540822006E11;
        final static Double VX = 23637.912321314047;
        final static Double VY = 11429.021426712032;

        final static Integer R = 255;
        final static Integer G = 0;
        final static Integer B = 0;

    }

    static class EARTH {
        final static Integer ID = 1;
        final static Double RADIUS = 6.371E6;
        final static Double MASS = 5.972E24;
        final static Double X = 1.391734353396533E11;
        final static Double Y = -5.71059040560652E10;
        final static Double VX = 10801.963811159256;
        final static Double VY = 27565.215006898345;

        final static Integer R = 0;
        final static Integer G = 0;
        final static Integer B = 255;

    }

    static class VENUS {
        final static Integer ID = 3;
        final static Double RADIUS = 6051800.0;
        final static Double MASS = 48.685E24;
        final static Double X = -4.666184828204109E10;
        final static Double Y = -9.793014564157066E+10;
        final static Double VX = 3.137593354538766E+4;
        final static Double VY = -1.521910639404947E+4;

        final static Integer R = 255;
        final static Integer G = 102;
        final static Integer B = 102;

    }

    static class MERCURY {
        final static Integer ID = 4;
        final static Double RADIUS = 2440000.0;
        final static Double MASS = 3.302e23;
        final static Double X = 4.691622470168644E+10;
        final static Double Y = 1.697841700672398E+10;
        final static Double VX = -2.603290734772638E+4;
        final static Double VY = 4.797372586694208E+4;

        final static Integer R = 255;
        final static Integer G = 128;
        final static Integer B = 0;

    }

    static class NEPTUNE {
        final static Integer ID = 5;
        final static Double RADIUS = 24766000.0;
        final static Double MASS = 102.41E24;
        final static Double X = 4.223255444234175E+12;
        final static Double Y = -1.496424779281588E+12;
        final static Double VX = 1.781238625245325E+03;
        final static Double VY = 5.143044974839391E+03;

        final static Integer R = 0;
        final static Integer G = 255;
        final static Integer B = 0;

    }

    static class URANUS {
        final static Integer ID = 6;
        final static Double RADIUS = 25559000.0;
        final static Double MASS = 86.8103E24;
        final static Double X = 2.767393256412390E+12;
        final static Double Y = 1.117231892558219E+12;
        final static Double VX = -2.596712386119689E+03;
        final static Double VY = 5.985198590799890E+03;

        final static Integer R = 76;
        final static Integer G = 0;
        final static Integer B = 153;

    }

    static class SATURN {
        final static Integer ID = 7;
        final static Double RADIUS = 60268000.0;
        final static Double MASS = 5.68319E26;
        final static Double X = -3.596008111078323E+11;
        final static Double Y = -1.457660355722258E+12;
        final static Double VX = 8.853834929507176E+03;
        final static Double VY = -2.352980594608253E+03;

        final static Integer R = 0;
        final static Integer G = 153;
        final static Integer B = 153;

    }

    static class JUPITER {
        final static Integer ID = 5;
        final static Double RADIUS = 71492000.0;
        final static Double MASS = 1898.13E24;
        final static Double X = -8.142425410407021E+11;
        final static Double Y = -4.043974156108359E+10;
        final static Double VX = 4.927089045889333E2;
        final static Double VY = -1.244634587636874E+04;

        final static Integer R = 127;
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
