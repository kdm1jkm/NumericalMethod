/*
 * Created by JFormDesigner on Sun Mar 21 19:35:14 KST 2021
 */

package com.github.kdm1jkm.numthd.GUI;

import com.github.kdm1jkm.numthd.DrawableGraph;
import com.github.kdm1jkm.numthd.Main;
import com.github.kdm1jkm.numthd.calc.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * @author unknown
 */
public class MainForm extends JFrame {
    public MainForm() {
        initComponents();
        update();
    }

    private double getEps() {
        if (radioButton_eps.isSelected()) {
            return (double) spinner_eps.getValue();
        } else {
            return getInterval() / getCount();
        }
    }

    private double getInterval() {
        return getMax() - getMin();
    }

    private double getMax() {
        return (double) spinner_max.getValue();
    }

    private double getMin() {
        return (double) spinner_min.getValue();
    }

    private int getCount() {
        if (radioButton_count.isSelected()) {
            return (int) spinner_count.getValue();
        } else {
            return (int) (getInterval() / getEps());
        }
    }

    private void btn_calculate_clicked(ActionEvent e) {
        new Thread(() -> {
            if (radioButton_original.isSelected()) {
                LoadingForm loading = new LoadingForm(2);
                loading.setVisible(true);
                loading.setValue(1);
                double[] xs = new IdentityFunc(getMin(), getMax(), getEps()).calculate(loading.progressBar);
                loading.setValue(2);
                double[] values;
                try {
                    values = new NormalFunc(xs, getExpression()).calculate(loading.progressBar);
                    if (Arrays.stream(values).allMatch(Double::isNaN)) {
                        throw new IllegalArgumentException("Illegal expression");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    ErrorDialog dialog = new ErrorDialog(this);
                    dialog.setContent(ex.getMessage());
                    dialog.setVisible(true);
                    return;
                } finally {

                    loading.setVisible(false);
                }
                Main.drawGraph(new DrawableGraph(xs, values, 0, 0, getCount(), "f(x)"));
            } else if (radioButton_derivate.isSelected()) {
                LoadingForm loading = new LoadingForm(3);
                loading.setVisible(true);
                loading.setValue(1);
                double[] xs = new IdentityFunc(getMin(), getMax(), getEps()).calculate(loading.progressBar);

                loading.setValue(2);
                double[] values;
                double[] diffs;

                try {
                    values = new NormalFunc(xs, getExpression()).calculate(loading.progressBar);
                    if (Arrays.stream(values).allMatch(Double::isNaN)) {
                        throw new IllegalArgumentException("Illegal expression");
                    }

                    loading.setValue(3);
                    diffs = new DiffFunc(values, getEps()).calculate(loading.progressBar);
                    if (Arrays.stream(diffs).allMatch(Double::isNaN)) {
                        throw new IllegalArgumentException("Illegal expression");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    ErrorDialog dialog = new ErrorDialog(this);
                    dialog.setContent(ex.getMessage());
                    dialog.setVisible(true);
                    return;
                } finally {
                    loading.setVisible(false);
                }
                Main.drawGraph(new DrawableGraph(xs, diffs, 0, 0, getCount(), "f'(x)"));
            } else if (radioButton_integral.isSelected()) {
                LoadingForm loadingForm = new LoadingForm(4);

                loadingForm.setValue(1);
                double[] xs = new IdentityFunc(getMin(),getMax(),getEps()).calculate(loadingForm.progressBar);
                loadingForm.setValue(2);
                double[] values = new NormalFunc(xs,getExpression()).calculate(loadingForm.progressBar);

                loadingForm.setValue(3);
                double result1 = new TrapezoidalRule(values, getEps()).calculate(loadingForm.progressBar);
                loadingForm.setValue(4);
                double result2 = new SimpsonRule(values, getEps()).calculate(loadingForm.progressBar);
                loadingForm.setVisible(false);

                if (Double.isNaN(result1) || Double.isNaN(result2)) {
                    ErrorDialog errorDialog = new ErrorDialog(this);
                    errorDialog.setContent(String.format("Can't integrate %s from %s to %s",getExpression(),getMin(),getMax()));
                    errorDialog.setVisible(true);
                    return;
                }

                IntegralResultForm form = new IntegralResultForm(getExpression(),getMin(),getMax(), result1, result2);
                form.setVisible(true);
            }
        }).start();
    }

    private String getExpression() {
        return txtFld_expression.getText();
    }

    private void spinner_minStateChanged(ChangeEvent e) {
        spinner_max.setValue(Math.max(getMax(), getMin()));
        update();
    }

    private void spinner_maxStateChanged(ChangeEvent e) {
        spinner_min.setValue(Math.min(getMin(), getMax()));
        update();
    }

    private void spinner_epsStateChanged(ChangeEvent e) {
        update();
    }

    private void update() {
        if (radioButton_count.isSelected()) {
            spinner_eps.setValue(getEps());
        } else {
            spinner_count.setValue(getCount());
        }
    }

    private void spinner_countStateChanged(ChangeEvent e) {
        update();
    }

    private void radioButton_Clicked(ActionEvent e) {
        spinner_eps.setEnabled(radioButton_eps.isSelected());
        spinner_count.setEnabled(radioButton_count.isSelected());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        label1 = new JLabel();
        txtFld_expression = new JTextField();
        separator1 = new JSeparator();
        label2 = new JLabel();
        label3 = new JLabel();
        separator2 = new JSeparator();
        btn_calculate = new JButton();
        spinner_min = new JSpinner();
        spinner_max = new JSpinner();
        spinner_eps = new JSpinner();
        spinner_count = new JSpinner();
        separator3 = new JSeparator();
        radioButton_derivate = new JRadioButton();
        radioButton_integral = new JRadioButton();
        radioButton_eps = new JRadioButton();
        radioButton_count = new JRadioButton();
        radioButton_original = new JRadioButton();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Expression");

        //---- txtFld_expression ----
        txtFld_expression.setText("sin(1/x)");

        //---- label2 ----
        label2.setText("from");

        //---- label3 ----
        label3.setText("to");

        //---- btn_calculate ----
        btn_calculate.setText("Calculate!");
        btn_calculate.addActionListener(e -> btn_calculate_clicked(e));

        //---- spinner_min ----
        spinner_min.setModel(new SpinnerNumberModel(-10.0, null, null, 1.0));
        spinner_min.addChangeListener(e -> spinner_minStateChanged(e));

        //---- spinner_max ----
        spinner_max.setModel(new SpinnerNumberModel(10.0, null, null, 1.0));
        spinner_max.addChangeListener(e -> spinner_maxStateChanged(e));

        //---- spinner_eps ----
        spinner_eps.setModel(new SpinnerNumberModel(0.001, 0.0, null, 0.001));
        spinner_eps.addChangeListener(e -> spinner_epsStateChanged(e));

        //---- spinner_count ----
        spinner_count.setModel(new SpinnerNumberModel(2000, 1, null, 1));
        spinner_count.setEnabled(false);
        spinner_count.addChangeListener(e -> spinner_countStateChanged(e));

        //---- radioButton_derivate ----
        radioButton_derivate.setText("take the derivate of f(x)");

        //---- radioButton_integral ----
        radioButton_integral.setText("integrate f(x)");

        //---- radioButton_eps ----
        radioButton_eps.setText("eps");
        radioButton_eps.setSelected(true);
        radioButton_eps.addActionListener(e -> radioButton_Clicked(e));

        //---- radioButton_count ----
        radioButton_count.setText("count");
        radioButton_count.addActionListener(e -> radioButton_Clicked(e));

        //---- radioButton_original ----
        radioButton_original.setText("draw graph of f(x)");
        radioButton_original.setSelected(true);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(separator1)
                        .addComponent(separator2)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFld_expression))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinner_min, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinner_max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                                                .addComponent(radioButton_eps)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinner_eps, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(radioButton_count)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinner_count, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(radioButton_original)
                                                .addGap(167, 167, 167)
                                                .addComponent(radioButton_derivate)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                                                .addComponent(radioButton_integral)))
                                .addContainerGap())
                        .addComponent(separator3)
                        .addComponent(btn_calculate, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.linkSize(SwingConstants.HORIZONTAL, spinner_count, spinner_eps, spinner_max, spinner_min);
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1)
                                        .addComponent(txtFld_expression, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator1, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(label3)
                                        .addComponent(spinner_max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinner_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(radioButton_count)
                                        .addComponent(spinner_min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinner_eps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(radioButton_eps))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(radioButton_original)
                                                .addComponent(radioButton_derivate))
                                        .addComponent(radioButton_integral))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_calculate)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton_derivate);
        buttonGroup1.add(radioButton_integral);
        buttonGroup1.add(radioButton_original);

        //---- buttonGroup ----
        var buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton_eps);
        buttonGroup.add(radioButton_count);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel label1;
    private JTextField txtFld_expression;
    private JSeparator separator1;
    private JLabel label2;
    private JLabel label3;
    private JSeparator separator2;
    private JButton btn_calculate;
    private JSpinner spinner_min;
    private JSpinner spinner_max;
    private JSpinner spinner_eps;
    private JSpinner spinner_count;
    private JSeparator separator3;
    private JRadioButton radioButton_derivate;
    private JRadioButton radioButton_integral;
    private JRadioButton radioButton_eps;
    private JRadioButton radioButton_count;
    private JRadioButton radioButton_original;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
