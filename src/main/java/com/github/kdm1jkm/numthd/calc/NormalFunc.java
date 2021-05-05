package com.github.kdm1jkm.numthd.calc;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

import javax.swing.*;

public class NormalFunc implements Func {
    private final double[] x;
    private final String expression;

    private double[] data = null;

    public NormalFunc(double[] x, String expression) {
        this.x = x;
        this.expression = expression;
    }

    @Override
    public double[] calculate(JProgressBar progressBar) {
        progressBar.setMinimum(0);
        progressBar.setMaximum(x.length);
        if (data == null) {
            data = new double[x.length];
            DoubleEvaluator evaluator = new DoubleEvaluator();
            StaticVariableSet<Double> variables = new StaticVariableSet<>();

            for (int i = 0; i < data.length; i++) {
                variables.set("x", x[i]);
                try {
                    data[i] = evaluator.evaluate(expression, variables);
                } catch (Exception e) {
                    data[i] = Double.NaN;
                    System.out.printf("x = %g\n", x[i]);
                    System.out.println(e.getMessage());
                    System.out.println("---------------------------");
                }
                progressBar.setValue(i);
            }
        }
        progressBar.setValue(data.length);

        return data;
    }
}
