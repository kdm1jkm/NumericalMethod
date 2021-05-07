package com.github.kdm1jkm.numthd;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.github.kdm1jkm.numthd.GUI.MainForm;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
//            UIManager.setLookAndFeel(new FlatDarculaLaf());
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainForm form = new MainForm();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setTitle("Numerical Method");
        form.setVisible(true);
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
