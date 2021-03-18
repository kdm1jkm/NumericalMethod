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

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        DoubleEvaluator evaluator = new DoubleEvaluator();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter expression>>");
        String expression = scanner.nextLine();

        System.out.print("Enter min>>");
        double min = scanner.nextDouble();

        System.out.print("Enter max>>");
        double max = scanner.nextDouble();

        System.out.print("Enter epsilon>>");
        double eps = scanner.nextDouble();

        scanner.close();

        int count = (int) abs((max - min) / eps);

        double[][] data = new double[count + 1][];

        StaticVariableSet<Double> variables = new StaticVariableSet<>();

        // Calculate
        for (int i = 0; i < data.length; i++) {
            double value = min + (max - min) / count * i;
            variables.set("x", value);
            data[i] = new double[2];
            data[i][0] = value;
            data[i][1] = evaluator.evaluate(expression, variables);
        }

        // File
        File file = new File("output.csv");
        try (OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file))) {
            for (double[] datum : data) {
                output.write(String.format("%f, %f\n", datum[0], datum[1]));
            }
        } catch (Exception e) {
            System.out.println("Error open file");
        }

        // Graph
        JavaPlot plot = new JavaPlot();

        DataSetPlot dataSet = new DataSetPlot(data);
        PlotStyle style = new PlotStyle();
        style.setStyle(Style.LINES);
        dataSet.setPlotStyle(style);

        plot.addPlot(dataSet);
        plot.plot();
    }
}
