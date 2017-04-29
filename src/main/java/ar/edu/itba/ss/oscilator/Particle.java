package ar.edu.itba.ss.oscilator;


/**
 * Created by maggie on 4/21/17.
 */
public class Particle {

    private Integer id;

    private double mass = 0.0;

    private double old_x_pos = 0.0;

    private double old_x_acc = 0.0;

    private double x_pos = 0.0;

    private double x_speed = 0.0;

    private double x_acc = 0.0;

    public Particle(int id, double mass, double x, double x_speed){
        this.id = id;
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

    public double getXAcc(){ return x_acc;}

    public void setXAcc(double x_acc) { this.x_acc = x_acc;}

    public double getXSpeed(){
        return x_speed;
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

    public void setXSpeed(Double x_speed) {
        this.x_speed = x_speed;
    }

    public void setOldXPos(double old_x_pos) {this.old_x_pos = old_x_pos;}

    public void setOldXAcc(double old_x_acc) {this.old_x_acc = old_x_acc;}

    public double getOldXAcc() {return old_x_acc;}

}