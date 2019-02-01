package com.billlynch.windtunnel;

public class Main {

    public static void main(String[] args) {
        final Worker worker = new Worker();
        final float output = worker.calculate();
        System.out.println(output);
    }
}
