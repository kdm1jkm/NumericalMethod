package com.github.kdm1jkm.numthd.calc;

import javax.swing.*;

public class IdentityFunc implements Func {

    private final double start;
    private final double end;
    private final double eps;

    private double[] data = null;

    public IdentityFunc(double start, double end, double eps) {
        this.start = start;
        this.end = end;
        this.eps = eps;
    }

    @Override
    public double[] calculate(JProgressBar progressBar) {
        int count = (int) ((end - start) / eps);
        progressBar.setMinimum(0);
        progressBar.setMaximum(count);
        if (data == null) {
            data = new double[count];


            for (int i = 0; i < data.length; i++) {
                data[i] = start + eps * i;
                progressBar.setValue(i);
            }
        }
        progressBar.setValue(data.length);
        return data;
    }
}
