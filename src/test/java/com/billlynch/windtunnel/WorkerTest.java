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
        final float expected = 0;
        final float output = this.instanceUnderTest.calculate();
        assertThat(output).isEqualTo(expected);
    }
}
