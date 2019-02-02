package com.billlynch.windtunnel;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Worker {

    public ReturnType calculate(double t1, double r1, double gamma,  double p1, double m1, double t4, double m4, double r4, double gama4, double p4) {
        double localSpeedOfSoundZone1 = Math.sqrt(gamma * r1 * t1);
        double densitityZone1 = p1 / r1 / t1;
        double localSpeedOfSoundZoon4 = Math.sqrt(gama4 * r4 * t4);
        double densitityZone4 = p4 / r4 / t4;

        double pressureZone2 = 0;
        double tempZone2 = 0;
        double fluidVelocity2 = 0;
        double machNumberZone2 = 0;
        double airDensityZone2 = 0;

        double pressureZone3 = 0;
        double tempZone3 = 0;
        double fluidVelocityZone3 = 0;
        double machNumberZone3 = 0;
        double airDensityZone3 = 0;



        return ReturnType.builder()
                .pressure2(pressureZone2)
                .pressure3(pressureZone3)
                .temp2(tempZone2)
                .temp3(tempZone3)
                .fluidVelocity2(fluidVelocity2)
                .fluidVelocity3(fluidVelocityZone3)
                .machNumber2(machNumberZone2)
                .machNumber3(machNumberZone3)
                .airDensity2(airDensityZone2)
                .airDensity3(airDensityZone3)
                .build();

    }
}
