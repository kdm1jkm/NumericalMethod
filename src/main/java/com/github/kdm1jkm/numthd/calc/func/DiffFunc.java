package com.github.kdm1jkm.numthd.calc.func;

import javax.swing.*;

public class DiffFunc implements Func {

    private final double[] fx;
    private final double eps;

    private double[] data = null;

    public DiffFunc(double[] fx, double eps) {
        this.fx = fx;
        this.eps = eps;
    }


    @Override
    public double[] calculate(JProgressBar progressBar) {
        progressBar.setMinimum(0);
        progressBar.setMaximum(fx.length);
        if (data == null) {
            data = new double[fx.length];
            for (int i = 1; i < data.length - 1; i++) {
                data[i] = (fx[i + 1] - fx[i - 1]) / (eps * 2);
                progressBar.setValue(i);
            }
            data[0] = Double.NaN;
            data[data.length - 1] = Double.NaN;
        }
        progressBar.setValue(data.length);
        return data;
    }
}
