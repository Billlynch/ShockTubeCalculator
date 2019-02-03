package com.billlynch.ShockTubeCalculator;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class ReturnType {
    private double pressure2;
    private double temp2;
    private double fluidVelocity2;
    private double machNumber2;
    private double airDensity2;

    private double ms;

    private double pressure3;
    private double temp3;
    private double fluidVelocity3;
    private double machNumber3;
    private double airDensity3;


    @Override
    public String toString() {
        return "Ms: " + ms + System.lineSeparator() +
                "Zone 2: " + System.lineSeparator() +
                "{" + System.lineSeparator() +
                " pressure=" + pressure2 + System.lineSeparator() +
                " temp=" + temp2 + System.lineSeparator() +
                " fluidVelocity=" + fluidVelocity2 + System.lineSeparator() +
                " machNumber=" + machNumber2 + System.lineSeparator() +
                " airDensity=" + airDensity2 + System.lineSeparator() + "}" +
                System.lineSeparator() +
                "Zone 3: " + System.lineSeparator() +
                "{" + System.lineSeparator() +
                " pressure=" + pressure3 + System.lineSeparator() +
                " temp=" + temp3 + System.lineSeparator() +
                " fluidVelocity=" + fluidVelocity3 + System.lineSeparator() +
                " machNumber=" + machNumber3 + System.lineSeparator() +
                " airDensity=" + airDensity3 + System.lineSeparator() +
                '}';
    }
}
