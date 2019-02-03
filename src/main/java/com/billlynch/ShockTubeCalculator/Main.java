package com.billlynch.ShockTubeCalculator;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;

@SuppressWarnings("squid:S106")
@Log
public class Main {

    public static void main(String[] args) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double tempZone1, rhoZone1, gammaZone1, pressureZone1;
        double tempZone4, rhoZone4, gammaZone4, pressureZone4;
        final Worker worker = new Worker();

        try {
            System.out.println("Zone 1");
            System.out.println("Input temp");
            tempZone1 = Double.parseDouble(reader.readLine());

            System.out.println("Input gas constant");
            rhoZone1 = Double.parseDouble(reader.readLine());

            System.out.println("Input specific heat ratio (gamma)");
            gammaZone1 = Double.parseDouble(reader.readLine());

            System.out.println("Input pressure");
            pressureZone1 = Double.parseDouble(reader.readLine());

            System.out.println("Zone 4");
            System.out.println("Input temp");
            tempZone4 = Double.parseDouble(reader.readLine());

            System.out.println("Input gas constant");
            rhoZone4 = Double.parseDouble(reader.readLine());

            System.out.println("Input specific heat ratio (gamma)");
            gammaZone4 = Double.parseDouble(reader.readLine());

            System.out.println("Input pressure");
            pressureZone4 = Double.parseDouble(reader.readLine());


            final ReturnType output = worker.calculate(tempZone1, rhoZone1, gammaZone1, pressureZone1, tempZone4, rhoZone4, gammaZone4, pressureZone4);
            System.out.println("Result:" + System.lineSeparator() + output);
        } catch (IOException e) {
            log.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        } catch (NumberFormatException e) {
            log.log(Level.SEVERE, "Your input was not a double. i.e.: '1.0'");
        }

    }
}
