package com.billlynch.windtunnel;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Worker {

    public ReturnType calculate(double t1, double r1, double gamma,  double p1, double m1, double t4, double m4, double r4, double gama4, double p4) {
        double a1 = Math.sqrt(gamma * r1 * t1);
        double rou1 = p1 / r1 / t1;
        double a4 = Math.sqrt(gama4 * r4 * t4);
        double rou4 = p4 / r4 / t4;

        double pressure2 = 0;
        double temp2 = 0;
        double fluidVelocity2 = 0;
        double machNumber2 = 0;
        double airDensity2 = 0;

        double pressure3 = 0;
        double temp3 = 0;
        double fluidVelocity3 = 0;
        double machNumber3 = 0;
        double airDensity3 = 0;



        return ReturnType.builder()
                .pressure2(pressure2)
                .pressure3(pressure3)
                .temp2(temp2)
                .temp3(temp3)
                .fluidVelocity2(fluidVelocity2)
                .fluidVelocity3(fluidVelocity3)
                .machNumber2(machNumber2)
                .machNumber3(machNumber3)
                .airDensity2(airDensity2)
                .airDensity3(airDensity3)
                .build();

    }
}
