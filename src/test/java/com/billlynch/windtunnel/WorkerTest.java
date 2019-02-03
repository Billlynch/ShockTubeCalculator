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
        double r1 = 287.0;
        double gamma = 1.4;
        double p1 = 1.01 * Math.pow(10,5);
        double m1 = 29;

        double t4 = 282.0;
        double m4 = 29.0;
        double r4 = 287;
        double gama4 = 1.4;
        double p4 = 3.04 * Math.pow(10,6);

        final ReturnType expected = ReturnType.builder()
                .ms(1.9620802219711537)
                .pressure2(436796.5782995688)
                .temp2(467.2546431651534)
                .fluidVelocity2(407.41744163022713)
                .machNumber2(0.9402813847693332)
                .airDensity2(3.2571945928702181)
                .pressure3(436796.5782995688)
                .temp3(161.997336766165)
                .fluidVelocity3(407.41744163022713)
                .machNumber3(1.59691103609522)
                .airDensity3(9.40501155286702)
                .build();


        final ReturnType output = this.instanceUnderTest.calculate(t1, r1, gamma, p1, m1, t4, m4, r4, gama4, p4);
        assertThat(output).isEqualTo(expected);
    }


    @Test
    public void testCase2() {
        double t1 = 282.0;
        double r1 = 287.0;
        double gamma = 1.4;
        double p1 = 0.2 * Math.pow(10,5);
        double m1 = 29;

        double t4 = 282.0;
        double m4 = 29.0;
        double r4 = 287;
        double gama4 = 1.4;
        double p4 = 3.04 * Math.pow(10,6);

        final ReturnType expected = ReturnType.builder()
                .pressure2(436796.5782995688)
                .temp2(467.2546431651534)
                .fluidVelocity2(407.41744163022713)
                .machNumber2(0.9402813847693332)
                .airDensity2(3.2571945928702181)
                .pressure3(436796.581146037)
                .temp3(161.997336766165)
                .fluidVelocity3(403.852920596334)
                .machNumber3(1.59691103609522)
                .airDensity3(9.40501155286702)
                .build();


        final ReturnType output = this.instanceUnderTest.calculate(t1, r1, gamma, p1, m1, t4, m4, r4, gama4, p4);
        assertThat(output).isEqualTo(expected);
    }


    @Test
    public void testMsFinder() {
        double expected = 1.9576632729409265;
        double actual = instanceUnderTest.findMs(1.01 * Math.pow(10, 5), 3.0 * Math.pow(10, 6),1.4,1.4, 290, 287 , 290, 287);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void calculateYwithMach1() {
        double y = -29.0;
        double actual = instanceUnderTest.calcYIteration(1.4, 1.4, 30.0, 1, 1);
        assertThat(actual).isEqualTo(y);

    }
}
