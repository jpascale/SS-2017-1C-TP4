package ar.edu.itba.ss.oscilator;


import ar.edu.itba.ss.io.Output;

import java.util.HashMap;
import java.util.Map;

public class Oscillator {

    private static double mass = 70.0;

    private static double k = Math.pow(10,4);
    private static double gamma = 100.0;

    private static double time;

    private static double x0 = 1.0;
    private static double v0 = -gamma / (2 * mass);

    private static Particle p;


    private Oscillator(double time){
        p = new Particle(0, 1.0, mass, x0, v0);
        p.setXAcc(getAcel(p));
        Oscillator.time = time;
    }

     static void Simulate(String filename, Type type, double deltaTime){
        Oscillator o = new Oscillator(5.0);

        Particle previous = new Particle(1,1.0, mass, o.eulerPos(p, -deltaTime), o.eulerVel(p, -deltaTime));
        p.setOldXPos(previous.getX());
        p.setOldXAcc(o.getAcel(previous));

        double diff = 0.0;
        int count = 0;

        for(double t=0.0;t<time;t+=deltaTime){
            diff += Math.pow(p.getX() - o.exactSol(t),2);

            Output.printOscillator(filename, p.getX(),t);

            o.update(type, deltaTime);
            count ++;
        }

        System.out.println("ERROR =" + diff/count);


    }

    private double getForce(Particle p){
        return -k * p.getX() - gamma * p.getXSpeed();
    }

    private double getForce(double x, double xspeed){
        return -k * x - gamma * xspeed;
    }

    private double getAcel(Particle p){
        return getForce(p) / p.getMass();
    }

    private double getAcel(double x, double xspeed) {
        return getForce(x, xspeed) / mass;
    }

    private double eulerPos(Particle p, double delta){
        return p.getX() + delta * p.getXSpeed() + ((Math.pow(delta, 2) * getForce(p)) / 2 * p.getMass());
    }

    private double eulerVel(Particle p, double delta){
        return p.getXSpeed() + (delta * getForce(p)) / p.getMass();
    }

    private double exactSol(double time){
        double aux = (k / mass) - (Math.pow(gamma, 2) / (4 * Math.pow(mass, 2)));
        return Math.exp(- (gamma / (2 * mass)) * time) * Math.cos(Math.sqrt(aux) * time);
    }


    private void update(Type type,double delta){
        switch(type) {
            case BEEMAN:
                BeeMan(delta);
                break;
            case GEARPREDICTOR:
                GearPredictor(delta);
                break;
            case VERLET:
                Verlet(delta);
                break;
        }

    }

    private void Verlet(double delta){
        Particle next = new Particle(1, mass);

        double nextX = p.getX() + delta * p.getXSpeed() + (delta*delta*getForce(p) / p.getMass());
        next.setX(nextX);

        double predXSpeed = eulerVel(p, delta);

        next.setXSpeed(predXSpeed);

        double nextSpeedX = p.getXSpeed() + (delta / (2 * p.getMass())) * (getForce(p) + getForce(next)) ;

        p.setOldXPos(p.getX());
        p.setOldXAcc(p.getXAcc());

        p.setX(next.getX());
        p.setXSpeed(nextSpeedX);
        p.setXAcc(getAcel(p));

    }

    private void BeeMan(double delta){
        Particle next = new Particle(1, mass);

        double newX = p.getX() + p.getXSpeed() * delta + (2.0 / 3.0) * p.getXAcc() * Math.pow(delta, 2) - (1.0 / 6.0) * p.getOldXAcc() * Math.pow(delta, 2);
        next.setX(newX);

        //PredictV
        double predXSpeed = p.getXSpeed() + (3.0 / 2.0) * p.getXAcc() * delta - (1.0/2.0) * p.getOldXAcc() * delta;

        next.setXSpeed(predXSpeed);
        next.setXAcc(getAcel(next));

        double newXSpeed = p.getXSpeed() + (1.0 / 3.0)*next.getXAcc() * delta + (5.0 / 6.0) * p.getXAcc() * delta - (1.0 / 6.0) * p.getOldXAcc() * delta;

        p.setOldXPos(p.getX());
        p.setOldXAcc(p.getXAcc());

        p.setX(next.getX());
        p.setXSpeed(newXSpeed);
        p.setXAcc(next.getXAcc());
    }

    private void GearPredictor(double delta){
        double deltaR2,deltaA;
        double [] coef = {3.0/16.0 , 251.0/360.0 , 1.0 , 11.0/18.0 , 1.0/6.0 , 1.0/60.0};

        Map<Integer,Double> rp;
        Map<Integer,Double> rc;

        //Initialize
        rc = new HashMap<>();
        rp = new HashMap<>();

        rc.put(0, p.getX());
        rc.put(1, p.getXSpeed());
        rc.put(2, getAcel(rc.get(0), rc.get(1)));
        rc.put(3, getAcel(rc.get(1), rc.get(2)));
        rc.put(4, getAcel(rc.get(2), rc.get(3)));
        rc.put(5, getAcel(rc.get(3), rc.get(4)));

        //Predict
        rp.put(0, rc.get(0) + rc.get(1)* delta + rc.get(2) * (Math.pow(delta, 2) / 2.0) + rc.get(3) * (Math.pow(delta, 3) / 6.0) + rc.get(4) * (Math.pow(delta, 4) / 24.0) + rc.get(5) * (Math.pow(delta, 5) / 120.0));
        rp.put(1, rc.get(1) + rc.get(2)* delta + rc.get(3) * (Math.pow(delta, 2) / 2.0) + rc.get(4) * (Math.pow(delta, 3) / 6.0) + rc.get(5) * (Math.pow(delta, 4) / 24.0));
        rp.put(2, rc.get(2) + rc.get(3)* delta + rc.get(4) * (Math.pow(delta, 3) / 2.0) + rc.get(5) * (Math.pow(delta, 4) / 6.0));
        rp.put(3, rc.get(3) + rc.get(4)* delta + rc.get(5) * (Math.pow(delta, 3) / 2.0));
        rp.put(4, rc.get(4) + rc.get(5)* delta);
        rp.put(5, rc.get(5));

        //Evaluate
        deltaA = getAcel(rp.get(0),rp.get(1)) - rp.get(2);
        deltaR2 = (deltaA * Math.pow(delta,2)) / 2;

        //Correct
        rc.put(0, rp.get(0) + coef[0] * deltaR2);
        rc.put(1, rp.get(1) + coef[1] * (deltaR2 / delta));
        rc.put(2, rp.get(2) + coef[2] * (deltaR2 * 2.0 / Math.pow(delta, 2)));
        rc.put(3, rp.get(3) + coef[3] * (deltaR2 * 6.0 / Math.pow(delta, 3)));
        rc.put(4, rp.get(4) + coef[4] * (deltaR2 * 24.0 / Math.pow(delta, 4)));
        rc.put(5, rp.get(5) + coef[5] * (deltaR2 * 120.0 / Math.pow(delta, 5)));

        //Update
        p.setX(rc.get(0));
        p.setXSpeed(rc.get(1));

    }

}
