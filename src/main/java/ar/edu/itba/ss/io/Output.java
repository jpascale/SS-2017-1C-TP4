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

public class Output {

    public static void printOscillator(String filename, double x, double time) {
        StringBuilder sb = new StringBuilder();
        sb.append(time + "\t" + x + "\n");
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }


}
