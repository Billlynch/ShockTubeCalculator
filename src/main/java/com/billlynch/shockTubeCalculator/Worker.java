package com.billlynch.shockTubeCalculator;

import lombok.NoArgsConstructor;

@NoArgsConstructor
class Worker {

    private static final int MAX = 500;
    private static final double TOLERANCE = 1 * Math.pow(10, -6);


    ReturnType calculate(double tempZone1, double rhoZone1, double gammaZone1, double pressureZone1,
                         double tempZone4, double rhoZone4, double gammaZone4, double pressureZone4) {
        final double airDensityZone1 = pressureZone1 / (rhoZone1 * tempZone1);
        final double pressureZone2, tempZone2, fluidVelocityZone2, machNumberZone2, airDensityZone2;
//        final double tempZone3, machNumberZone3, airDensityZone3;


        //calculate mS
        double mS = findMs(pressureZone1, pressureZone4, gammaZone4, gammaZone1, tempZone1, rhoZone1, tempZone4, rhoZone4);
        final double msSquared = Math.pow(mS, 2.0);

        // calculate zone 2
        pressureZone2 = calculatePressureZone2(gammaZone1, pressureZone1, msSquared);
        airDensityZone2 = calculateAirDensityZone2(gammaZone1, airDensityZone1, msSquared);
        tempZone2 = calculateTemperatureZone2(tempZone1, pressureZone1, airDensityZone1, pressureZone2, airDensityZone2);
        machNumberZone2 = calculateMachNumberZone2(gammaZone1, msSquared);
        fluidVelocityZone2 = calculateFluidVelocityZone2(rhoZone1, gammaZone1, tempZone2, machNumberZone2);


        return ReturnType.builder()
                .ms(mS)
                .pressure2(pressureZone2)
                .pressure3(pressureZone2) // p2 = p3
                .temp2(tempZone2)
                //.temp3(tempZone3) //TODO tempZone3
                .fluidVelocity2(fluidVelocityZone2)
                .fluidVelocity3(fluidVelocityZone2) // u2 = u3
                .machNumber2(machNumberZone2)
                //.machNumber3(machNumberZone3) //TODO machNumberZone3
                .airDensity2(airDensityZone2)
                //.airDensity3(airDensityZone3) //TODO airDensityZone3
                .build();

    }

    double calculateFluidVelocityZone2(double rhoZone1, double gammaZone1, double tempZone2, double machNumberZone2) {
        return machNumberZone2 * Math.sqrt(gammaZone1 * rhoZone1 * tempZone2);
    }

    double calculateMachNumberZone2(double gammaZone1, double msSquared) {
        double sqrtFirstHalf = ((gammaZone1 - 1) * msSquared) + 2;
        double sqrtSecondHalf = (2 * gammaZone1 * msSquared) - (gammaZone1 - 1);
        return (2 * (msSquared - 1)) / Math.sqrt(sqrtFirstHalf * sqrtSecondHalf);
    }

    double calculateTemperatureZone2(double tempZone1, double pressureZone1, double airDensityZone1, double pressureZone2, double airDensityZone2) {
        return (pressureZone2 / pressureZone1) * (airDensityZone1 / airDensityZone2) * tempZone1;
    }

    double calculateAirDensityZone2(double gammaZone1, double airDensityZone1, double msSquared) {
        return (((gammaZone1 + 1) * msSquared) / (((gammaZone1 - 1) * msSquared) + 2)) * airDensityZone1;
    }

    double calculatePressureZone2(double gammaZone1, double pressureZone1, double msSquared) {
        return (((2 * gammaZone1 * msSquared) - (gammaZone1 - 1)) / (gammaZone1 + 1)) * pressureZone1;
    }

    double findMs(double p1, double p4, double gammaZone4, double gammaZone1, double tempZone1, double rhoZone1, double tempZone4, double rhoZone4) {
        final double p41 = p4 / p1;

        double localSpeedOfSoundZone1 = Math.sqrt(gammaZone1 * rhoZone1 * tempZone1);
        double localSpeedOfSoundZone4 = Math.sqrt(gammaZone4 * rhoZone4 * tempZone4);
        final double speedOfSoundRatio = localSpeedOfSoundZone1 / localSpeedOfSoundZone4;
        double ms0 = 2.2;
        double ms1 = 2.5;
        double msIteration0;
        double msIteration1;
        double absoulteDifference;
        double ms2 = 0;


        for (int i = 0; i < MAX; i++) {
            msIteration0 = calcYIteration(gammaZone4, gammaZone1, p41, ms0, speedOfSoundRatio);
            msIteration1 = calcYIteration(gammaZone4, gammaZone1, p41, ms1, speedOfSoundRatio);

            ms2 = ms1 - ((msIteration1) / ((msIteration1 - msIteration0) / (ms1 - ms0)));

            absoulteDifference = Math.abs(ms2 - ms0);
            if (absoulteDifference < TOLERANCE) {
                break;
            }
            ms0 = ms1;
            ms1 = ms2;
        }

        return ms2;
    }

    double calcYIteration(double gammaZone4, double gammaZone1, double p41, double nX, double a14) {
        double yAlpha = ((2 * gammaZone1 * Math.pow(nX, 2.0)) - (gammaZone1 - 1)) / (gammaZone1 + 1);
        double yBetaBase = 1 - ((a14 * ((gammaZone4 - 1) / (gammaZone1 + 1))) * (nX - (1 / nX)));
        double yBeta = Math.pow(yBetaBase, (-2 * gammaZone4) / (gammaZone4 - 1));
        return yAlpha * yBeta - p41;
    }

}
