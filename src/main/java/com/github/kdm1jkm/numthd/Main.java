package com.github.kdm1jkm.numthd;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.github.kdm1jkm.numthd.GUI.MainForm;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
//            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainForm form = new MainForm();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setTitle("Numerical Method");
        form.setVisible(true);
    }

    public static double[] getDiffs(double[] values, double eps) {
        double[] result = new double[values.length];
        for (int i = 1; i < result.length - 1; i++) {
            double value = (values[i + 1] - values[i - 1]) / (eps * 2);
            result[i] = value;
        }
        return result;
    }

    public static double[] getValues(String expression, double[] xs) {
        double[] result = new double[xs.length];
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        DoubleEvaluator evaluator = new DoubleEvaluator();
        for (int i = 0; i < result.length; i++) {
            variables.set("x", xs[i]);
            double value = 0;
            try {
                value = evaluator.evaluate(expression, variables);
            } catch (IllegalArgumentException e) {
                value = Double.NaN;
            }
            result[i] = value;
        }
        return result;
    }

    public static double[] getXs(double min, double max, int count) {
        double[] xs = new double[count];
        for (int i = 0; i < count; i++) {
            double value = min + (max - min) / count * i;
            xs[i] = value;
        }
        return xs;
    }

    private static void printElapsed(long start) {
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void exportFile(double[][] data, File file) {
        try (OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file))) {
            for (double[] datum : data) {
                output.write(String.format("%f, %f\n", datum[0], datum[1]));
            }
        } catch (Exception e) {
            System.out.println("Error open file");
        }
    }

    public static void drawGraph(DrawableGraph... graphs) {
        JavaPlot plot = new JavaPlot();

        for (var graph : graphs) {
            DataSetPlot dataSet = new DataSetPlot(graph.values);
            dataSet.setTitle(graph.name);
            PlotStyle style = new PlotStyle();
            style.setStyle(Style.LINES);
            dataSet.setPlotStyle(style);
            plot.addPlot(dataSet);
        }

        plot.plot();
    }
}
