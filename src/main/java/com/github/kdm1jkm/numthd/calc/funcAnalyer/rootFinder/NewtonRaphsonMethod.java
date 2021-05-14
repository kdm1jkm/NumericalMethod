package com.github.kdm1jkm.numthd.calc.funcAnalyer.rootFinder;

import com.github.kdm1jkm.numthd.calc.funcAnalyer.FuncAnalyzer;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;

public class NewtonRaphsonMethod implements RootFinder {
    private final String expression;
    private final int iteration;
    private final double initX;

    public NewtonRaphsonMethod(double initX, String expression, int iteration) {
        this.initX = initX;
        this.expression = expression;
        this.iteration = iteration;
    }

    @Override
    public double calculate(JProgressBar progressBar) {
        Argument x = new Argument("x", initX);
        Expression originalExpression = new Expression(expression, x);
        Expression derivativeExpression = new Expression(String.format("der(%s,x)", expression), x);

        progressBar.setMinimum(0);
        progressBar.setMaximum(iteration);

        for (int i = 0; i < iteration; i++) {
            progressBar.setValue(i);

            x.setArgumentValue(x.getArgumentValue() - originalExpression.calculate() / derivativeExpression.calculate());
        }

        return originalExpression.calculate();
    }
}
