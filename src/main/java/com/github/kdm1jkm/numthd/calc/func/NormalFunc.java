package com.github.kdm1jkm.numthd.calc.func;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;

public class NormalFunc implements Func {
    private final double[] xs;
    private final String expression;

    private double[] data = null;

    public NormalFunc(double[] xs, String expression) {
        this.xs = xs;
        this.expression = expression;
    }

    @Override
    public double[] calculate(JProgressBar progressBar) {
        progressBar.setMinimum(0);
        progressBar.setMaximum(xs.length);
        if (data == null) {
            data = new double[xs.length];
            Argument x = new Argument("x");
            Expression expression = new Expression(this.expression, x);

            for (int i = 0; i < data.length; i++) {
                x.setArgumentValue(xs[i]);
                try {
                    data[i] = expression.calculate();
                } catch (Exception e) {
                    data[i] = Double.NaN;
                    System.out.printf("x = %g\n", xs[i]);
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
