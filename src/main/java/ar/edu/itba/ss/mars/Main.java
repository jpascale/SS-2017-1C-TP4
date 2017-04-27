package ar.edu.itba.ss.mars;

import ar.edu.itba.ss.io.Output;
import ar.edu.itba.ss.oscilator.Particle;

import java.util.ArrayList;
import ar.edu.itba.ss.mars.SpaceData.*;
import ar.edu.itba.ss.mars.Planet.Component;

public class Main {
    private static ArrayList<Planet> planets = new ArrayList<>();
    private static double deltaTime = 100; // seconds
    private static double printTime = 43200; // 12 hours in seconds
//    private static double printTime = 60; // 12 hours in seconds

//    private static double time = 31536000; // year in seconds
    private static double time = 2592000; // 30 days in seconds


    public static void main(String[] args) {
        Planet sun = new Planet(SUN.ID, SUN.RADIUS, SUN.MASS, SUN.X, SUN.Y, SUN.VX, SUN.VY);
        Planet earth = new Planet(EARTH.ID, EARTH.RADIUS, EARTH.MASS, EARTH.X, EARTH.Y, EARTH.VX, EARTH.VY);
        Planet mars = new Planet(MARS.ID, MARS.RADIUS, MARS.MASS, MARS.X, MARS.Y, MARS.VX, MARS.VY);
        //Planet ship = new Planet(SHIP.ID, SHIP.RADIUS, SHIP.MASS, SHIP.X, SHIP.Y, SHIP.VX, SHIP.VY);

        sun.setColor(SUN.R, SUN.G, SUN.B);
        earth.setColor(EARTH.R, EARTH.G, EARTH.B);
        mars.setColor(MARS.R, MARS.G, MARS.B);
//      ship.setColor(SHIP.R, SHIP.G, SHIP.B);

        planets.add(sun);
        planets.add(earth);
        planets.add(mars);
//        planets.add(ship);

        setPreviousPos();

        Simulate();

        System.out.println("TERMINO");
    }


    private static void Simulate() {
        int printCont = 0;

        for (double t = 0; t < time; t += deltaTime) {
            if (printTime * printCont <= t) {
                Output.printSystem(planets, t);
                printCont++;
            }

            ArrayList<Planet> aux  = new ArrayList<>();
            copy(aux, planets);

            for (Planet p : planets) {
                updatePosition(p, deltaTime, aux);
            }
        }

    }

    private static void copy(ArrayList<Planet> destination, ArrayList<Planet> source){
        for(Planet p: source){
            destination.add(p);
        }
    }

    private static Double eulerPos(double pos, double speed, double force, double mass, double delta){
        return pos + delta * speed + (Math.pow(delta,2) * force) / (2.0 * mass);
    }

    private static void setPreviousPos() {
        for(Planet p : planets){
            p.setOldX(eulerPos(p.getX(), p.getXSpeed(), p.getComponentForce(planets, Component.X), p.getMass(), -deltaTime));
            p.setOldY(eulerPos(p.getY(), p.getYSpeed(), p.getComponentForce(planets, Component.Y), p.getMass(), -deltaTime));

        }
    }

    private static void updatePosition(Planet p, double delta, ArrayList<Planet> positions) {
        double newX = 2.0 * p.getX() - p.getOldX() + (Math.pow(delta, 2) * p.getComponentForce(positions, Component.X)) / p.getMass();
        double newY = 2.0 * p.getY() - p.getOldY() + (Math.pow(delta, 2) * p.getComponentForce(positions, Component.Y)) / p.getMass();

        p.setOldX(p.getX());
        p.setOldY(p.getY());

        p.setX(newX);
        p.setY(newY);

    }


//    private static double totalKineticEnergy(){
//        double sum = 0.0;
//        for(int i=0;i<particles.size();i++){
//            for(int j=i+1;j<particles.size();j++){
//                sum += calculateGravityForce(particles.get(i),particles.get(j));
//            }
//        }
//        return sum;
//    }


//    private static double totalPotentialEnergy(){
//        double sum = 0.0;
//        for(int i=0;i<particles.size();i++){
//            for(int j=i+1;j<particles.size();j++){
//                sum += calculatePotentialEnergy(particles.get(i),particles.get(j));
//            }
//        }
//        return sum;
//    }

    private static double calculatePotentialEnergy(Particle p1, Particle p2) {
        return - (SpaceData.G * p1.getMass() * p2.getMass()) / p1.getDistance(p2);
    }
}

