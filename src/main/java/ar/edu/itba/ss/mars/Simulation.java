package ar.edu.itba.ss.mars;


import java.util.ArrayList;

public class Simulation {

    private final ArrayList<Planet> planets = new ArrayList<>();
    private final SimulationAnswer sa = new SimulationAnswer();

    private final long runningTime;
    private final long deltaTime;
    private final long printTime;

    public Simulation(ArrayList<Planet> planets, long runningTime, long deltaTime, long printTime) {
        this.runningTime = runningTime;
        this.deltaTime = deltaTime;
        this.printTime = printTime;

        planets.forEach((planet) -> this.planets.add(planet.clone()));
    }

    public void Simulate() {
        int printCont = 0;

        for (long t = 0; t < runningTime; t += deltaTime) {
            if (printTime * printCont <= t) {
                sa.writeAnswer(planets, t);
                printCont++;
            }

            // Save previous positions
            ArrayList<Planet> aux = new ArrayList<>();
            planets.forEach((planet) -> aux.add(planet.clone()));

            for (Planet p : planets) {
                updatePosition(p, deltaTime, aux);
            }
        }

        sa.printAnswer();
    }

    private void updatePosition(Planet p, double delta, ArrayList<Planet> positions) {
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

}
