package ar.edu.itba.ss.io;


import ar.edu.itba.ss.mars.Planet;
import ar.edu.itba.ss.mars.SpaceData;
import ar.edu.itba.ss.oscilator.Particle;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maggie on 4/22/17.
 */
public class Output {

    public static void printOscillator(String filename, double x, double time) {
        StringBuilder sb = new StringBuilder();
        sb.append(time + " " + x + "\n");
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void animateOscillator(String outputFile, String inputFile, double printTime,double totalTime) {
        double print = 0.0;
        File f = new File(inputFile);
        try{
            Scanner scanner = new Scanner(f);
            while(print + printTime < totalTime){
                String [] s = scanner.nextLine().split("\\s+");
                double t = Double.parseDouble(s[0]);
                double pos = Double.parseDouble(s[1]);
                if (print <= t) {
                    printOscillator(outputFile,pos,t);
                    print += printTime;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static double [] radius = {SpaceData.SUN.RADIUS * 100 , SpaceData.EARTH.RADIUS * 1000, SpaceData.MARS.RADIUS * 1000};


    public static void printSystem(List<Planet> particles, double t) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t" + particles.size() + "\n");
        sb.append("\t" + t/86400 + "\n");
        for(Planet p: particles){
            sb.append("\t" + p.getId() + "\t" + p.getX() + "\t" + p.getY() + "\t" + radius[p.getId()]  + "\t" + p.getR() + "\t" + p.getG() + "\t" + p.getB() + "\n");
        }

        try {
            FileWriter fw = new FileWriter("outMars.txt", true);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
