package ar.edu.itba.ss.oscilator;


/**
 * Created by maggie on 4/21/17.
 */
public class Particle {

    private Integer id;

    private double radius = 0.0;
    private double mass = 0.0;

    private double old_x_pos = 0.0;
    private double old_y_pos = 0.0;

    private double old_x_acc = 0.0;
    private double old_y_acc = 0.0;

    private double x_pos = 0.0;
    private double y_pos = 0.0;

    private double x_speed = 0.0;
    private double y_speed = 0.0;

    private double x_acc = 0.0;
    private double y_acc = 0.0;


    public Particle(int id, double radius, double mass, double x, double y, double x_speed, double y_speed){
        this.id = id;
        this.radius = radius;
        this.mass = mass;
        this.x_pos = x;
        this.y_pos = y;
        this.x_speed = x_speed;
        this.y_speed = y_speed;
    }

    public Particle(int id,double mass){
        this.id = id;
        this.mass = mass;
    }

    public Particle(int id, double radius, double mass, double x, double x_speed){
        this.id = id;
        this.radius = radius;
        this.mass = mass;
        this.x_pos = x;
        this.x_speed = x_speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        return id.equals(particle.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Double getY() {
        return y_pos;
    }

    public void setY(double y_pos) {
        this.y_pos = y_pos;
    }

    public double getXAcc(){ return x_acc;}

    public double getYAcc(){ return y_acc;}

    public void setXAcc(double x_acc) { this.x_acc = x_acc;}

    public void setYAcc(double y_acc) { this.y_acc = y_acc;}

    public double getXSpeed(){
        return x_speed;
    }

    public double getYSpeed(){
        return y_speed;
    }

    public Double getX() {
        return x_pos;
    }

    public void setX(double x_pos) {
        this.x_pos = x_pos;
    }

    public Double getMass() {
        return mass;
    }

    public Double getRadius() {
        return radius;
    }

    public void setXSpeed(Double x_speed) {
        this.x_speed = x_speed;
    }

    public void setYSpeed(Double y_speed) {
        this.y_speed = y_speed;
    }

    public void setOldXPos(double old_x_pos) {this.old_x_pos = old_x_pos;}

    public void setOldXAcc(double old_x_acc) {this.old_x_acc = old_x_acc;}

    public double getOldYPos() { return old_y_pos; }

    public void setOldYPos(double old_y_pos) { this.old_y_pos = old_y_pos; }

    public double getOld_y_acc() { return old_y_acc; }

    public void setOld_y_acc(double old_y_acc) { this.old_y_acc = old_y_acc; }

    public double getOldXAcc() {return old_x_acc;}

    public double getOldXPos() {return old_x_pos;}


    @Override
    public String toString() {
        return "Particle " + this.id + " (" + this.x_pos + "," + this.y_pos + ")(" + this.x_speed + "," + this.y_speed + ")";
    }

    public Integer getId() {
        return id;
    }

    public double getSpeed() {
        return Math.sqrt(Math.pow(getXSpeed(), 2)+ Math.pow(getYSpeed(), 2));
    }


    public double getPosition() {
        return Math.sqrt(Math.pow(getX(), 2)+ Math.pow(getY(), 2));
    }

    public double getDistance(Particle p){
        double x = getX()-p.getX();
        double y = getY()-p.getY();
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}