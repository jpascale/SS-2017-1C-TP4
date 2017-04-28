package ar.edu.itba.ss.mars;

import java.util.ArrayList;


public class Planet {

    private Integer id;

    private double radius = 0.0;
    private double mass = 0.0;

    private double old_x_pos = 0.0;
    private double old_y_pos = 0.0;

    private double x_pos = 0.0;
    private double y_pos = 0.0;

    private double x_speed = 0.0;
    private double y_speed = 0.0;

    private int R = 0;
    private int G = 0;
    private int B = 0;


    public Planet(int id, double radius, double mass, double x, double y, double x_speed, double y_speed){
        this.id = id;
        this.radius = radius;
        this.mass = mass;
        this.x_pos = x;
        this.y_pos = y;
        this.x_speed = x_speed;
        this.y_speed = y_speed;
    }

    public Planet(int id, double radius, double mass) {
        this.id = id;
        this.radius = radius;
        this.mass = mass;
    }

    public Double getComponentForce (ArrayList<Planet> planetList, Component component) {

        double c1 = 0.0;
        double c2 = 0.0;

        double sum = 0.0;

        for (Planet p : planetList) {
            if (!p.equals(this)) {
                switch (component) {
                    case X:
                        c1 = this.x_pos;
                        c2 = p.getX();
                        break;

                    case Y:
                        c1 = this.y_pos;
                        c2 = p.getY();
                        break;
                }

                double force = getGravityForce(p);
                double e = (c2 - c1) / Math.abs(getDistance(p));
                sum += force * e;
                
            }
        }

        return sum;

    }

    /**
     * Returns the gravity force in module in the component of the distance between another planet.
     * @param p The planet
     * @return The gravity force
     */
    public Double getGravityForce(Planet p){
        return (SpaceData.G * this.getMass() * p.getMass()) / Math.pow(getDistance(p), 2);
    }


    public Double getDistance(Planet p){
        return Math.sqrt(Math.pow(p.getX() - this.getX(), 2) + Math.pow(p.getY() - this.getY(), 2));
    }

    /**
     * Generates a deep copy of the planet
     * @return The planet copy
     */
    public Planet clone(){
        Planet newPlanet = new Planet(id, radius, mass, x_pos, y_pos, x_speed, y_speed);
        newPlanet.setOldX(old_x_pos);
        newPlanet.setOldY(old_y_pos);
        newPlanet.setColor(R, G, B);

        return newPlanet;
    }

    public Integer getId() {
        return id;
    }

    public double getX() {
        return x_pos;
    }

    public void setX(double x_pos) {
        this.x_pos = x_pos;
    }

    public double getY() {
        return y_pos;
    }

    public void setY(double y_pos) {
        this.y_pos = y_pos;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getOldX() {
        return old_x_pos;
    }

    public void setOldX(double old_x_pos) {
        this.old_x_pos = old_x_pos;
    }

    public double getOldY() {
        return old_y_pos;
    }

    public void setOldY(double old_y_pos) {
        this.old_y_pos = old_y_pos;
    }

    public double getXSpeed() {
        return x_speed;
    }

    public void setXSpeed(double x_speed) {
        this.x_speed = x_speed;
    }

    public double getYSpeed() {
        return y_speed;
    }

    public void setYSpeed(double y_speed) {
        this.y_speed = y_speed;
    }

    public void setColor(int R, int G, int B){
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public int getR() { return this.R; }

    public int getG() { return this.G; }

    public int getB() { return this.B; }

    public enum Component {
        X,
        Y,
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planet planet = (Planet) o;

        return id != null ? id.equals(planet.id) : planet.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
