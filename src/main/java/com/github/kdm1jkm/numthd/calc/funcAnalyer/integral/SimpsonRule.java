package com.github.kdm1jkm.numthd.calc.funcAnalyer.integral;

import com.github.kdm1jkm.numthd.calc.funcAnalyer.FuncAnalyzer;

import javax.swing.*;

public class SimpsonRule implements FuncAnalyzer {
    private final double[] values;
    private final double delta;

    public SimpsonRule(double[] values, double delta) {
        this.values = values;
        this.delta = delta;
    }


    @Override
    public double calculate(JProgressBar progressBar) {
        double result = 0;
        progressBar.setMinimum(0);
        progressBar.setMaximum(values.length - 1);
        progressBar.setValue(0);

        for (int i = 0; i < values.length; i++) {
            if (i == 0 || i == values.length - 1) {
                result += values[i];
            } else if (i % 2 == 0) {
                result += values[i] * 4;
            } else {
                result += values[i] * 2;
            }
        }

        result *= delta;
        result /= 3;

        return result;
    }
}
