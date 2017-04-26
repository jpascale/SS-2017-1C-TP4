package ar.edu.itba.ss.mars;

import ar.edu.itba.ss.io.Output;
import ar.edu.itba.ss.oscilator.Particle;

import java.util.LinkedList;
import java.util.List;


public class Main {
    private static List<Particle> particles = new LinkedList<>();

    public static void main(String[] args){
        System.out.println("HOLA");
    }

    private Main(){
        Planet sun = new Particle(0,695700,1.988E30,0,0,0,0);
        Particle earth = new Particle(1,6371,5.972E24,1.391734353396533E8,-0.571059040560652E8,10.801963811159256,27.565215006898345);
        Particle mars = new Particle(2,3389.9,6.4185E23,0.831483493435295E8,-1.914579540822006E8,23.637912321314047,11.429021426712032);
        //Particle asteroid = new Particle(3,1,2E5,1,1,1,1);;

        particles.add(mars);
        particles.add(sun);
        particles.add(earth);
        //particles.add(asteroid);

    }

    public static void Simulate(double time, double deltaTime, double printTime){
        int printCont = 0;
        int count = 0;

        for(double t=0;t<time;t+=deltaTime) {
            if(printTime*printCont <=t){

                Output.printSystem(particles,t);
                printCont ++;
            }
            for(Particle p: particles){
                update(p,deltaTime);
            }
        }

    }


    // Usar esto que es Verlet original o BeeMAn
    private static void update(Particle p, double delta){
        double newX = 2.0 * p.getX() - p.getOldXPos() + (delta*delta * calculateForceX(p))/p.getMass();
        double newY = 2.0 * p.getY() - p.getOldYPos() + (delta*delta * calculateForceY(p))/p.getMass();

        p.setOldXPos(p.getX());
        p.setOldYPos(p.getY());
        p.setX(newX);
        p.setY(newY);

    }


    private static double calculateE(Particle p1, Particle p2) {
        return 0;
    }

    private static double totalKineticEnergy(){
        double sum = 0.0;
        for(int i=0;i<particles.size();i++){
            for(int j=i+1;j<particles.size();j++){
                sum += calculateGravityForce(particles.get(i),particles.get(j));
            }
        }
        return sum;
    }


    private static double totalPotentialEnergy(){
        double sum = 0.0;
        for(int i=0;i<particles.size();i++){
            for(int j=i+1;j<particles.size();j++){
                sum += calculatePotentialEnergy(particles.get(i),particles.get(j));
            }
        }
        return sum;
    }

    private static double calculatePotentialEnergy(Particle p1,Particle p2){
        return -(G*p1.getMass()*p2.getMass())/p1.getDistance(p2);
    }

