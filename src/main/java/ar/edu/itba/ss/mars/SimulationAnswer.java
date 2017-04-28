package ar.edu.itba.ss.mars;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationAnswer {

    private StringBuilder sb = new StringBuilder();

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
            FileWriter fw = new FileWriter("outMars.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
