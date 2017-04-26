package ar.edu.itba.ss.mars;

import ar.edu.itba.ss.io.Output;
import ar.edu.itba.ss.oscilator.Particle;

import java.util.ArrayList;
import ar.edu.itba.ss.mars.SpaceData.*;

public class Main {
    private static ArrayList<Planet> planets = new ArrayList<>();
    private static double deltaTime = 1;
    private static double printTime = 1;
    private static double time = 1;


    public static void main(String[] args) {
        System.out.println("HOLA");
    }

    private Main() {
        Planet sun = new Planet(SUN.ID, SUN.RADIUS, SUN.MASS, SUN.X, SUN.Y, SUN.VX, SUN.VY);
        Planet earth = new Planet(EARTH.ID, EARTH.RADIUS, EARTH.MASS, EARTH.X, EARTH.Y, EARTH.VX, EARTH.VY);
        Planet mars = new Planet(MARS.ID, MARS.RADIUS, MARS.MASS, MARS.X, MARS.Y, MARS.VX, MARS.VY);
        Planet ship = new Planet(SHIP.ID, SHIP.RADIUS, SHIP.MASS, SHIP.X, SHIP.Y, SHIP.VX, SHIP.VY);

        planets.add(mars);
        planets.add(sun);
        planets.add(earth);
        planets.add(ship);

        setPreviousPos();

        Simulate(time);

    }


    public static void Simulate(double time) {
        int printCont = 0;

        for (double t = 0; t < time; t += deltaTime) {
            if (printTime * printCont <= t) {

                Output.printSystem(planets, t);
                printCont++;
            }
            for (Planet p : planets) {
                update(p, deltaTime);
            }
        }

    }

    public Double eulerPos(double pos, double speed, double force, double mass, double delta){
        return pos + delta * speed + (delta * delta * force) / (2.0 * mass);
    }

    private void setPreviousPos() {
        for(Planet p : planets){
            p.setOldX(eulerPos(p.getX(), p.getXSpeed(), p.getComponentForce(planets, Planet.Component.X), p.getMass(), -deltaTime));
            p.setOldY(eulerPos(p.getY(), p.getYSpeed(), p.getComponentForce(planets, Planet.Component.Y), p.getMass(), -deltaTime));
        }
    }


    private static void update(Planet p, double delta) {
        double newX = 2.0 * p.getX() - p.getOldX() + (delta * delta * p.getComponentForce(planets, Planet.Component.X)) / p.getMass();
        double newY = 2.0 * p.getY() - p.getOldY() + (delta * delta * p.getComponentForce(planets, Planet.Component.Y)) / p.getMass();

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
        return -(SpaceData.G * p1.getMass() * p2.getMass()) / p1.getDistance(p2);
    }
}

