package com.github.kdm1jkm.numthd;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter expression>>");
        String expression = scanner.nextLine();

        System.out.print("Enter min>>");
        double min = scanner.nextDouble();

        System.out.print("Enter max>>");
        double max = scanner.nextDouble();

        System.out.print("Enter eps>>");
        double eps = scanner.nextDouble();

        int count = (int) ((max - min) / eps) + 1;

        scanner.close();

        long start = System.currentTimeMillis();

        double[] xs = getXs(min, max, count);
        double[] values = getValues(expression, xs);
        double[] diffs = getDiffs(values, eps);

        printElapsed(start);

        drawGraph(
                new DrawableGraph(xs, values, 0, 0, xs.length, expression),
                new DrawableGraph(xs, diffs, 0, 0, diffs.length, "d/dx " + expression)
        );
    }

    private static double[] getDiffs(double[] values, double eps) {
        double[] result = new double[values.length];
        for (int i = 1; i < result.length - 1; i++) {
            double value = (values[i + 1] - values[i - 1]) / (eps * 2);
            result[i] = value;
        }
        return result;
    }

    private static double[] getValues(String expression, double[] xs) {
        double[] result = new double[xs.length];
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        DoubleEvaluator evaluator = new DoubleEvaluator();
        for (int i = 0; i < result.length; i++) {
            variables.set("x", xs[i]);
            double value = evaluator.evaluate(expression, variables);
            result[i] = value;
        }
        return result;
    }

    private static double[] getXs(double min, double max, int count) {
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

    private static void exportFile(double[][] data, File file) {
        try (OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file))) {
            for (double[] datum : data) {
                output.write(String.format("%f, %f\n", datum[0], datum[1]));
            }
        } catch (Exception e) {
            System.out.println("Error open file");
        }
    }

    private static void drawGraph(DrawableGraph... graphs) {
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
