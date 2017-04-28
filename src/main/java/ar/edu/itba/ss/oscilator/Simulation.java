package ar.edu.itba.ss.oscilator;

import ar.edu.itba.ss.io.Output;

public class Simulation {

    private static double yearInMars = 687*24*60*60;
    private static double printTime = 12*60*60;

    public static void main( String[] args ) {

        Oscillator.Simulate("Simulation.txt",Type.GEARPREDICTOR,0.001);
        Output.animateOscillator("Animation.txt","Simulation.txt", 0.01, 5);

//        Main.Simulate(yearInMars,1,printTime);
//        System.out.println("TERMINO");

    }
}
