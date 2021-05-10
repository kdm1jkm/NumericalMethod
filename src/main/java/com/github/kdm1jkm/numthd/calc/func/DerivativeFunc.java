package com.github.kdm1jkm.numthd.calc.func;

import javax.swing.*;

public class DerivativeFunc implements Func {
    private final NormalFunc func;

    public DerivativeFunc(double[] xs, String expression) {
        func = new NormalFunc(xs, String.format("der(%s,x)", expression));
    }

    @Override
    public double[] calculate(JProgressBar progressBar) {
        return func.calculate(progressBar);
    }
}
