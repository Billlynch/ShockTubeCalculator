package com.billlynch.windtunnel;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Worker {
    double g1, g2;

    final static double GAS_CONSTANT= 287;

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

        double Ms = findMs(p1, p4, gama4, gamma, localSpeedOfSoundZone1, localSpeedOfSoundZoon4);





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

    private double findMs(double p1, double p4, double gammaZone4, double gammaZone1, double localSpeedOfSoundZone1, double localSpeedOfSoundZone4) {
        final double p41 = p4 / p1;
        final int MAX = 500;
        final double tolerance = 1 * Math.pow(10,-6);
        final double a14 = localSpeedOfSoundZone1 / localSpeedOfSoundZone4;
        double ms0 = 2.3;
        double ms1 = 4.5;
        double msIteration0 = 0;
        double msIteration1 = 0;
        double absoulteDifference = 0;
        double ms2 = 0;


        for (int i = 0; i < MAX; i++) {
            msIteration0 = calcYIteration(gammaZone4, gammaZone1, p41, ms0, a14);
            msIteration1 = calcYIteration(gammaZone4, gammaZone1, p41, ms1, a14);

            ms2 =  ms1 - (msIteration1/(msIteration1 - msIteration0 / ms1 - ms0));

            absoulteDifference = Math.abs(ms2 - ms0);
            if (absoulteDifference < tolerance) {
                break;
            }
            ms0 = ms1;
            ms1 = ms2;
        }


        return ms2;
    }

    private double calcYIteration(double gammaZone4, double gammaZone1, double p41, double n_x, double a14) {
        double Ms, y_alpha, y_beta_base, y_beta;
        Ms = n_x;
        y_alpha = 2 * gammaZone1 *  Ms - (gammaZone1 - 1)/ ( gammaZone1  + 1);
        y_beta_base = 1 - a14 * ((gammaZone4 - 1) / gammaZone1 + 1 ) *  (Ms - (1 / Ms));
        y_beta = Math.pow(y_beta_base, ((-2 * gammaZone4 ) / (gammaZone4 - 1)));
        return y_alpha * y_beta - p41;
    }
}
