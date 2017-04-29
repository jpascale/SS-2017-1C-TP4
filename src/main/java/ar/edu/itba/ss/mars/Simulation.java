package ar.edu.itba.ss.mars;


import java.util.ArrayList;
import ar.edu.itba.ss.mars.SpaceData.*;

public class Simulation {

    private final ArrayList<Planet> planets = new ArrayList<>();
    private final SimulationAnswer sa = new SimulationAnswer();

    private final long runningTime;
    private final long deltaTime;
    private final long printTime;

    Simulation(ArrayList<Planet> planets, long runningTime, long deltaTime, long printTime) {
        this.runningTime = runningTime;
        this.deltaTime = deltaTime;
        this.printTime = printTime;

        planets.forEach((planet) -> this.planets.add(planet.clone()));
    }


    SimulationAnswer Simulate() {
        int printCont = 0;
        double minDistance =  planets.get(MARS.ID).getDistance(planets.get(SHIP.ID));

        for (long t = 0; t < runningTime; t += deltaTime) {
            if (printTime * printCont <= t) {
                sa.writeAnswer(planets, t);
                printCont++;
            }
            double distance = planets.get(MARS.ID).getDistance(planets.get(SHIP.ID));
            if(minDistance > distance){
                minDistance = distance;
            }

            // Save previous positions
            ArrayList<Planet> aux = new ArrayList<>();
            planets.forEach((planet) -> aux.add(planet.clone()));

            for (Planet p : planets) {
                updatePosition(p, deltaTime, aux);
            }
            if(missionSuccess()){
                sa.writeArrivalSpeed(planets.get(SHIP.ID).getXSpeed(), planets.get(SHIP.ID).getYSpeed());
                sa.writeArrivalTime(t);
                sa.writeSuccess();
                return sa;
            }
        }
        System.out.println("MIN DISTANCE: " + minDistance);

        return sa;
    }

    public void updatePosition(Planet p, double delta, ArrayList<Planet> positions) {
        double newX = 2.0 * p.getX() - p.getOldX() + (Math.pow(delta, 2) * p.getComponentForce(positions, Planet.Component.X)) / p.getMass();
        double newY = 2.0 * p.getY() - p.getOldY() + (Math.pow(delta, 2) * p.getComponentForce(positions, Planet.Component.Y)) / p.getMass();

        double newXSpeed = p.getXSpeed() + delta * p.getComponentForce(positions, Planet.Component.X) / p.getMass();
        double newYSpeed = p.getYSpeed() + delta * p.getComponentForce(positions, Planet.Component.Y) / p.getMass();

        p.setOldX(p.getX());
        p.setOldY(p.getY());

        p.setX(newX);
        p.setY(newY);

        p.setXSpeed(newXSpeed);
        p.setYSpeed(newYSpeed);
    }

    private boolean missionSuccess(){
        Planet ship = planets.get(SHIP.ID);
        Planet mars = planets.get(MARS.ID);

        return ship.getDistance(mars) <= MARS.RADIUS + SpaceData.d;
    }

}
