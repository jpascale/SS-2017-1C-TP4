package ar.edu.itba.ss.mars;


import java.util.ArrayList;
import ar.edu.itba.ss.mars.SpaceData.*;

public class Simulation {

    private final ArrayList<Planet> planets = new ArrayList<>();
    private final SimulationAnswer sa = new SimulationAnswer();

    private final double runningTime;
    private final double deltaTime;
    private final long printTime;

    Simulation(ArrayList<Planet> planets, double runningTime, long deltaTime, long printTime) {
        this.runningTime = runningTime;
        this.deltaTime = deltaTime;
        this.printTime = printTime;

        planets.forEach((planet) -> this.planets.add(planet.clone()));
    }


    SimulationAnswer Simulate() {
        double minDistance = planets.get(MARS.ID).getDistance(planets.get(SHIP.ID));
        double taux = 0;
        double minvx = 0 , minvy = 0;
        int printCont = 0;

        for (double t = 0; t < runningTime; t += deltaTime) {
//            System.out.println("DIA: " + t);
//            System.out.println("VELOCIDAD  ( " + (planets.get(SHIP.ID).getXSpeed() - planets.get(MARS.ID).getXSpeed()) + " , " +  (planets.get(SHIP.ID).getYSpeed() - planets.get(MARS.ID).getYSpeed()) + " )");
            if (printTime * printCont <= t) {
                sa.writeAnswer(planets, t);
                printCont++;
            }
            double distance = planets.get(MARS.ID).getDistance(planets.get(SHIP.ID));
            if(minDistance > distance){
                taux = t;
                minDistance = distance;
                minvx = (planets.get(SHIP.ID).getXSpeed() - planets.get(MARS.ID).getXSpeed());
                minvy = (planets.get(SHIP.ID).getYSpeed() - planets.get(MARS.ID).getYSpeed());
            }

            // Save previous positions
            ArrayList<Planet> aux = new ArrayList<>();
            planets.forEach((planet) -> aux.add(planet.clone()));

            for (Planet p : planets) {
                updatePosition(p, deltaTime, aux);
            }
        }
        System.out.println("MINDISTANCE " + minDistance + " TIEMPO " + (int)(taux/Main.launchTime) + " dias");
        System.out.println("Velocidad en t min: " + Math.sqrt(Math.pow(minvx, 2) + Math.pow(minvy, 2)));
        return sa;
    }

    public static void updatePosition(Planet p, double delta, ArrayList<Planet> positions) {
        double newX = 2.0 * p.getX() - p.getOldX() + (Math.pow(delta, 2) * p.getComponentForce(positions, Planet.Component.X)) / p.getMass();
        double newY = 2.0 * p.getY() - p.getOldY() + (Math.pow(delta, 2) * p.getComponentForce(positions, Planet.Component.Y)) / p.getMass();

        double XSpeed = (newX - p.getOldX()) / (2 * delta);
        double YSpeed = (newY - p.getOldY()) / (2 * delta);

        p.setOldX(p.getX());
        p.setOldY(p.getY());

        p.setX(newX);
        p.setY(newY);

        p.setXSpeed(XSpeed);
        p.setYSpeed(YSpeed);
    }

    private boolean missionSuccess(Planet ship, Planet mars){
        return ship.getDistance(mars) <= MARS.RADIUS + SpaceData.d + SHIP.RADIUS;
    }

}
