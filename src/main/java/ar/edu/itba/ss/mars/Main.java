package ar.edu.itba.ss.mars;

import java.util.ArrayList;
import ar.edu.itba.ss.mars.SpaceData.*;
import ar.edu.itba.ss.mars.Planet.Component;

public class Main {
    private static SimulationAnswer sa = new SimulationAnswer();

    private static ArrayList<Planet> planets = new ArrayList<>();
    private static long deltaTime = 100; // seconds
    private static long printTime = 43200; // 12 hours in seconds

    private static long runningTime = 47335428; // year and a half in seconds

    private static double shipSpeed = 3000; // m/s
    private static long launchTime = 604800; //week in seconds

    public static void main(String[] args) {
        Simulation s;

        Planet sun = new Planet(SUN.ID, SUN.RADIUS, SUN.MASS, SUN.X, SUN.Y, SUN.VX, SUN.VY);
        Planet earth = new Planet(EARTH.ID, EARTH.RADIUS, EARTH.MASS, EARTH.X, EARTH.Y, EARTH.VX, EARTH.VY);
        Planet mars = new Planet(MARS.ID, MARS.RADIUS, MARS.MASS, MARS.X, MARS.Y, MARS.VX, MARS.VY);
        Planet ship = new Planet(SHIP.ID, SHIP.RADIUS, SHIP.MASS);

        sun.setColor(SUN.R, SUN.G, SUN.B);
        earth.setColor(EARTH.R, EARTH.G, EARTH.B);
        mars.setColor(MARS.R, MARS.G, MARS.B);
        ship.setColor(SHIP.R, SHIP.G, SHIP.B);

        planets.add(sun);
        planets.add(earth);
        planets.add(mars);

        planets.forEach(Main::setPreviousPos);

        planets.add(ship);
        setShipStartPosition(ship,earth,sun);

        for (long t = 0; t <= runningTime; t += launchTime) {
            planets.forEach((planet) -> evolvePlanet(planet, deltaTime, launchTime, planets));
            setShipStartPosition(ship, earth, sun);

            s = new Simulation(planets, runningTime, deltaTime, printTime);

            sa.setLaunchTime(t);
            sa = s.Simulate();

            sa.printStatistics();
            sa.printAnswer();

        }

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

       setPreviousPos(ship);

    }

    private static void evolvePlanet(Planet p, long delta, long evolvingTime, ArrayList<Planet> planets) {
        for (long t = 0; t <= evolvingTime; t += delta){
            double newX = 2.0 * p.getX() - p.getOldX() + (Math.pow(delta, 2) * p.getComponentForce(planets, Planet.Component.X)) / p.getMass();
            double newY = 2.0 * p.getY() - p.getOldY() + (Math.pow(delta, 2) * p.getComponentForce(planets, Planet.Component.Y)) / p.getMass();

            double newXSpeed = p.getXSpeed() + delta * p.getComponentForce(planets, Planet.Component.X) / p.getMass();
            double newYSpeed = p.getYSpeed() + delta * p.getComponentForce(planets, Planet.Component.Y) / p.getMass();

            p.setOldX(p.getX());
            p.setOldY(p.getY());

            p.setX(newX);
            p.setY(newY);

            p.setXSpeed(newXSpeed);
            p.setYSpeed(newYSpeed);
        }

    }

}

