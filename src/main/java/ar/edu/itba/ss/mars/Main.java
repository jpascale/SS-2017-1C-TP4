package ar.edu.itba.ss.mars;

import ar.edu.itba.ss.io.Output;
import ar.edu.itba.ss.oscilator.Particle;

import java.util.ArrayList;
import ar.edu.itba.ss.mars.SpaceData.*;
import ar.edu.itba.ss.mars.Planet.Component;

public class Main {
    private static Simulation s;

    private static ArrayList<Planet> planets = new ArrayList<>();
    private static long deltaTime = 100; // seconds
    private static long printTime = 43200; // 12 hours in seconds

    private static long time = 31536000; // year in seconds

    private static double shipSpeed = 3000; // m/s
    private static long launchTime = 604800; //week in seconds


    public static void main(String[] args) {
        Planet sun = new Planet(SUN.ID, SUN.RADIUS, SUN.MASS, SUN.X, SUN.Y, SUN.VX, SUN.VY);
        Planet earth = new Planet(EARTH.ID, EARTH.RADIUS, EARTH.MASS, EARTH.X, EARTH.Y, EARTH.VX, EARTH.VY);
        Planet mars = new Planet(MARS.ID, MARS.RADIUS, MARS.MASS, MARS.X, MARS.Y, MARS.VX, MARS.VY);
        Planet ship = new Planet(SHIP.ID, SHIP.RADIUS, SHIP.MASS);

        sun.setColor(SUN.R, SUN.G, SUN.B);
        earth.setColor(EARTH.R, EARTH.G, EARTH.B);
        mars.setColor(MARS.R, MARS.G, MARS.B);
        ship.setColor(SHIP.R, SHIP.G, SHIP.B);

        setShipStartPosition(ship, earth, sun);

        planets.add(sun);
        planets.add(earth);
        planets.add(mars);
        planets.add(ship);

        planets.forEach(Main::setPreviousPos);

        s = new Simulation(planets, time, deltaTime, printTime);
        s.Simulate();

        System.out.println("TERMINO");
    }

    private static Double eulerPos(double pos, double speed, double force, double mass, double delta){
        return pos + delta * speed - (Math.pow(delta,2) * force) / (2.0 * mass);
    }

    private static void setPreviousPos(Planet p) {
        p.setOldX(eulerPos(p.getX(), p.getXSpeed(), p.getComponentForce(planets, Component.X), p.getMass(), -deltaTime));
        p.setOldY(eulerPos(p.getY(), p.getYSpeed(), p.getComponentForce(planets, Component.Y), p.getMass(), -deltaTime));

    }

    private static void setShipStartPosition(Planet ship, Planet earth, Planet sun){
       double newX = earth.getX() - sun.getX() + (SpaceData.d + earth.getRadius()) * (earth.getX() - sun.getX()) / sun.getDistance(earth);
       double newY = earth.getY() - sun.getY() + (SpaceData.d + earth.getRadius()) * (earth.getY() - sun.getY()) / sun.getDistance(earth);

       double speedX = (shipSpeed + earth.getXSpeed()) * (earth.getX() - sun.getX()) / sun.getDistance(earth);
       double speedY = (shipSpeed + earth.getYSpeed()) * (earth.getY() - sun.getY()) / sun.getDistance(earth);

       ship.setX(newX);
       ship.setY(newY);

       ship.setXSpeed(speedX);
       ship.setYSpeed(speedY);

    }

}

