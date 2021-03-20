package com.github.kdm1jkm.numthd;

public class DrawableGraph {
    public final double[][] values;
    public final String name;

    public DrawableGraph(double[] arr1, double[] arr2, int start1, int start2, int count, String name) {
        values = new double[count][];
        this.name = name;

        for (int i = 0; i < values.length; i++) {
            values[i] = new double[2];

            values[i][0] = arr1[i + start1];
            values[i][1] = arr2[i + start2];
        }
    }
}
