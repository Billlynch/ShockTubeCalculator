package com.billlynch.windtunnel;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class WorkerTest {

    Worker instanceUnderTest;


    @Before
    public void setUp() throws Exception {
        instanceUnderTest = new Worker();
    }

    @Test
    public void testCase1() {
        double t1 = 282.0;
        double r1 = 282.0;
        double gamma = 1.4;
        double p1 = 1.01 * Math.exp(5); // not sure on this
        double m1 = 29;

        double t4 = 282.0;
        double m4 = 29.0;
        double r4 = 8314; // (/ m4)
        double gama4 = 1.4;
        double p4 = 3.04 * Math.exp(6);

        final ReturnType expected = ReturnType.builder()
                .pressure2(436796.581146036)
                .temp2(467.254635692246)
                .fluidVelocity2(403.852920596334)
                .machNumber2(0.940281410851192)
                .airDensity2(3.31494634466011)
                .pressure3(436796.581146037)
                .temp3(161.997336766165)
                .fluidVelocity3(403.852920596334)
                .machNumber3(1.59691103609522)
                .airDensity3(9.40501155286702)
                .build();


        final ReturnType output = this.instanceUnderTest.calculate(t1, r1, gamma, p1, m1, t4, m4, r4, gama4, p4);
        assertThat(output).isEqualTo(expected);
    }
}
