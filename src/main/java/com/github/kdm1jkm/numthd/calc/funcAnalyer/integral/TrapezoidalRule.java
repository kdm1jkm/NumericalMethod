package com.github.kdm1jkm.numthd.calc.funcAnalyer.integral;

import com.github.kdm1jkm.numthd.calc.funcAnalyer.FuncAnalyzer;

import javax.swing.*;

public class TrapezoidalRule implements FuncAnalyzer {
    private final double[] values;
    private final double delta;

    public TrapezoidalRule(double[] values, double delta) {
        this.values = values;
        this.delta = delta;
    }

    @Override
    public double calculate(JProgressBar progressBar) {
        progressBar.setMinimum(0);
        progressBar.setMaximum(values.length - 1);
        progressBar.setValue(0);

        double result = 0;
        for (int i = 0; i < values.length; i++) {
            if (i == 0 || i == values.length - 1) {
                result += values[i];
            } else {
                result += values[i] * 2;
            }
            progressBar.setValue(i);
        }

        result *= delta;
        result /= 2;

        return result;
    }
}
