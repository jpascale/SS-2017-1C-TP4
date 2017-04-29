package ar.edu.itba.ss.mars;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationAnswer {

    private StringBuilder sb = new StringBuilder();
    private boolean success = false;

    private long arrivalTime;

    //Arriving speeds
    private double xSpeed;
    private double ySpeed;

    private static double[] radius = {SpaceData.SUN.RADIUS * 10, SpaceData.EARTH.RADIUS * 1000, SpaceData.MARS.RADIUS * 1000, SpaceData.SHIP.RADIUS * 1000000};

    public void writeAnswer(ArrayList<Planet> planets, long t){
        sb.append("\t").append(planets.size()).append("\n");
        sb.append("\t").append(t / 86400).append("\n");

        for(Planet p: planets){
            sb.append("\t").append(p.getId()).append("\t").append(p.getX()).append("\t").append(p.getY()).append("\t").append(radius[p.getId()]).append("\t").
                    append(p.getR()).append("\t").append(p.getG()).append("\t").append(p.getB()).append("\n");
        }

    }

    public void printAnswer(){
        try {
            FileWriter fw = new FileWriter("outMars.txt", true);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeArrivalSpeed(double xSpeed, double ySpeed){
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

    }

    public void writeSuccess(){
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void writeArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void printStatistics() {
        StringBuilder sb = new StringBuilder();

        try {
            FileWriter fw = new FileWriter("statisticsMars.txt", true);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
