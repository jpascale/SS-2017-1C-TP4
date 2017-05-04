package ar.edu.itba.ss.mars;

import java.util.ArrayList;

import ar.edu.itba.ss.mars.SpaceData.*;
import ar.edu.itba.ss.mars.Planet.Component;

public class Main {
    private static SimulationAnswer sa = new SimulationAnswer();

    private static ArrayList<Planet> planets = new ArrayList<>();
    private static long deltaTime = 100; // seconds
    private static long printTime = 43200; // 12 hours in seconds

    private static long runningTime = 63072000 ; // two years in seconds

    private static double shipSpeed = 8000; // 4.2 km/s -> m/s
    private static double groundSpeed = 7120;

    public static long launchTime = 3600 * 24;

    public static void main(String[] args) {
        Simulation s;

        Planet sun = new Planet(SUN.ID, SUN.RADIUS, SUN.MASS, SUN.X, SUN.Y, SUN.VX, SUN.VY);
        Planet earth = new Planet(EARTH.ID, EARTH.RADIUS, EARTH.MASS, EARTH.X, EARTH.Y, EARTH.VX, EARTH.VY);
        Planet mars = new Planet(MARS.ID, MARS.RADIUS, MARS.MASS, MARS.X, MARS.Y, MARS.VX, MARS.VY);
        Planet ship = new Planet(SHIP.ID, SHIP.RADIUS, SHIP.MASS);

        planets.add(sun);
        planets.add(earth);
        planets.add(mars);

        planets.forEach(Main::setPreviousPos);

        planets.add(ship);
        setShipStartPosition(ship,earth,sun);

//        for (long t = 0; t <= runningTime; t += launchTime) {
//            System.out.println("Dia: " + t/launchTime);
//            evolveSystem(deltaTime, launchTime, planets);
//            setShipStartPosition(ship, earth, sun);
//
//            s = new Simulation(planets, runningTime, deltaTime, printTime);
//
//            sa = s.Simulate();
//
//            if ((t / launchTime) == 661) {
//                sa.printStatistics();
//                sa.printAnswer();
//            }
//
//        }

        evolveSystem(deltaTime, 663* launchTime, planets);
        setShipStartPosition(ship, earth, sun);

        s = new Simulation(planets, runningTime, deltaTime, printTime);

        sa = s.Simulate();
        sa.printAnswer();

        System.out.println("TERMINO");
    }

    private static Double eulerPos(double pos, double speed, double force, double mass, double delta){
        return pos + delta * speed + (Math.pow(delta,2) * force) / (2.0 * mass);
    }

    private static void setPreviousPos(Planet p) {
        p.setOldX(eulerPos(p.getX(), p.getXSpeed(), 0.0, p.getMass(), -deltaTime));
        p.setOldY(eulerPos(p.getY(), p.getYSpeed(), 0.0, p.getMass(), -deltaTime));

    }

    //TODO: CHECK
    private static void setShipStartPosition(Planet ship, Planet earth, Planet sun){
       double newX = earth.getX() - sun.getX() + (SpaceData.d + earth.getRadius()) * (earth.getX() - sun.getX()) / sun.getDistance(earth);
       double newY = earth.getY() - sun.getY() + (SpaceData.d + earth.getRadius()) * (earth.getY() - sun.getY()) / sun.getDistance(earth);

       double speedX = ((shipSpeed + groundSpeed) * (earth.getX() - sun.getX()) / sun.getDistance(earth)) + earth.getXSpeed();
       double speedY = ((shipSpeed + groundSpeed) * (earth.getY() - sun.getY()) / sun.getDistance(earth)) + earth.getYSpeed();

       ship.setX(newX);
       ship.setY(newY);

       ship.setXSpeed(speedX);
       ship.setYSpeed(speedY);

        ship.setOldX(eulerPos(ship.getX(), ship.getXSpeed(), ship.getComponentForce(planets, Component.X), ship.getMass(), -deltaTime));
        ship.setOldY(eulerPos(ship.getY(), ship.getYSpeed(), ship.getComponentForce(planets, Component.Y), ship.getMass(), -deltaTime));

    }

    private static void evolveSystem(long deltaTime, long launchTime, ArrayList<Planet> planets) {
        for(long t = 0; t <= launchTime; t += deltaTime){
            // Save previous positions
            ArrayList<Planet> aux = new ArrayList<>();
            planets.forEach((planet) -> aux.add(planet.clone()));

            for (Planet p : planets) {
                Simulation.updatePosition(p, deltaTime, aux);
            }
        }

    }

}

