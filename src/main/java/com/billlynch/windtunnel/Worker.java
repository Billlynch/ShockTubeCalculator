package com.billlynch.windtunnel;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Worker {
    double g1, g2;

    final static double GAS_CONSTANT= 287;

    public ReturnType calculate(double t1, double r1, double gamma,  double p1, double m1, double t4, double m4, double r4, double gama4, double p4) {
        double airDensityZone1 = p1 / (r1 * t1);
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

        double Ms = findMs(p1, p4, gama4, gamma,t1,r1,t4, r4);
        final double msSquared = Math.pow(Ms, 2.0);

        pressureZone2 = ( (2 * gamma * msSquared - (gamma - 1))/ ( gamma  + 1) ) * p1 ;
        airDensityZone2 = ((gamma +1) * Math.pow(Ms, 2)) / ( ((gamma -1 ) * Math.pow(Ms,2) )+ 2) * airDensityZone1;

        tempZone2 = (pressureZone2 / p1) * (airDensityZone1 / airDensityZone2) * t1;

        double sqrt_alpha = ((gamma - 1) * msSquared) + 2;
        double sqrt_beta = (2 * gamma * msSquared) - (gamma -1);
        machNumberZone2 = (2 * (msSquared - 1)) / Math.sqrt(sqrt_alpha * sqrt_beta);

        fluidVelocity2 = machNumberZone2 * Math.sqrt(gamma * r1 * tempZone2);

        return ReturnType.builder()
                .ms(Ms)
                .pressure2(pressureZone2)
                .pressure3(pressureZone2)
                .temp2(tempZone2)
                .temp3(tempZone3)
                .fluidVelocity2(fluidVelocity2)
                .fluidVelocity3(fluidVelocity2)
                .machNumber2(machNumberZone2)
                .machNumber3(machNumberZone3)
                .airDensity2(airDensityZone2)
                .airDensity3(airDensityZone3)
                .build();

    }

    public double findMs(double p1, double p4, double gammaZone4, double gammaZone1, double temp1, double r1, double temp4, double r4) {
        final double p41 = p4 / p1;
        final int MAX = 500;
        final double tolerance = 1 * Math.pow(10,-6);

        double localSpeedOfSoundZone1 = Math.sqrt(gammaZone1 * r1 * temp1);
        double localSpeedOfSoundZone4 = Math.sqrt(gammaZone4 * r4 * temp4);
        final double a14 = localSpeedOfSoundZone1 / localSpeedOfSoundZone4;
        double ms0 = 2.2;
        double ms1 = 2.5;
        double msIteration0;
        double msIteration1;
        double absoulteDifference = 0;
        double ms2 = 0;


        for (int i = 0; i < MAX; i++) {
            msIteration0 = calcYIteration(gammaZone4, gammaZone1, p41, ms0, a14);
            msIteration1 = calcYIteration(gammaZone4, gammaZone1, p41, ms1, a14);

            ms2 =  ms1 - ((msIteration1)/((msIteration1 - msIteration0) / (ms1 - ms0)));

            absoulteDifference = Math.abs(ms2 - ms0);
            if (absoulteDifference < tolerance) {
                break;
            }
            ms0 = ms1;
            ms1 = ms2;
        }


        return ms2;
    }

    public double calcYIteration(double gammaZone4, double gammaZone1, double p41, double n_x, double a14) {
        double Ms, y_alpha, y_beta_base, y_beta;
        Ms = n_x;
        y_alpha = (2 * gammaZone1 *  Math.pow(Ms, 2.0) - (gammaZone1 - 1))/ ( gammaZone1  + 1);
        y_beta_base = 1 - (a14 * ((gammaZone4 -1) / (gammaZone1 + 1))) * (Ms - (1/Ms));
        y_beta = Math.pow(y_beta_base, (-2 * gammaZone4 ) / (gammaZone4 - 1));
        return y_alpha * y_beta - p41;
    }

}
