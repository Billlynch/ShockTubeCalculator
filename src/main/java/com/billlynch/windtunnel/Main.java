package com.billlynch.windtunnel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double t1 = 282.0;
        double r1 = 282.0;
        double gamma1 = 1.4;
        double p1 = 1.01 * Math.exp(5); // not sure on this
        double m1 = 29;

        double t4 = 282.0;
        double m4 = 29.0;
        double r4 = 8314; // (/ m4)
        double gamma4 = 1.4;
        double p4 = 3.04 * Math.exp(6);

        final Worker worker = new Worker();

        try {
            System.out.println("Zone 1");
            System.out.println("Input temp");
            t1 = Double.parseDouble(reader.readLine());

            System.out.println("Input gas constant");
            r1 = Double.parseDouble(reader.readLine());

            System.out.println("Input specific heat ratio (gamma)");
            gamma1 = Double.parseDouble(reader.readLine());

            System.out.println("Input pressure");
            p1 = Double.parseDouble(reader.readLine());

            System.out.println("Input Molar Mass");
            m1 = Double.parseDouble(reader.readLine());

            System.out.println("Zone 4");
            System.out.println("Input temp");
            t4 = Double.parseDouble(reader.readLine());

            System.out.println("Input gas constant");
            r4 = Double.parseDouble(reader.readLine());

            System.out.println("Input specific heat ratio (gamma)");
            gamma4 = Double.parseDouble(reader.readLine());

            System.out.println("Input pressure");
            p4 = Double.parseDouble(reader.readLine());

            System.out.println("Input Molar Mass");
            m4 = Double.parseDouble(reader.readLine());

            final ReturnType output = worker.calculate(t1, r1, gamma1, p1, m1, t4, m4, r4, gamma4, p4);
            System.out.println("Result:" + System.lineSeparator() + output);
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e) {
            System.out.println("Your input was not a double. i.e.: '1.0'");
        }

    }
}
