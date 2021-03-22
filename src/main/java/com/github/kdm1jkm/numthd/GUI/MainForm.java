/*
 * Created by JFormDesigner on Sun Mar 21 19:35:14 KST 2021
 */

package com.github.kdm1jkm.numthd.GUI;

import com.github.kdm1jkm.numthd.DrawableGraph;
import com.github.kdm1jkm.numthd.Main;

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
    }

    private double getEps() {
        return (double) spinner_eps.getValue();
    }

    private double getMax() {
        return (double) spinner_max.getValue();
    }

    private double getMin() {
        return (double) spinner_min.getValue();
    }

    private int getCount() {
        return (int) ((getMax() - getMin()) / getEps());
    }

    private void btn_calculate_clicked(ActionEvent e) {
        new Thread(() -> {
            double[] xs = Main.getXs(getMin(), getMax(), getCount());
            double[] values;

            try {
                values = Main.getValues(txtFld_expression.getText(), xs);
                if (Arrays.stream(values).allMatch(Double::isNaN)) {
                    throw new IllegalArgumentException("Illegal expression");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                ErrorDialog dialog = new ErrorDialog(this);
                dialog.setVisible(true);
                return;
            }

            double[] diffs = Main.getDiffs(values, getEps());

            Main.drawGraph(
                    new DrawableGraph(xs, values, 0, 0, getCount(), "f(x)"),
                    new DrawableGraph(xs, diffs, 0, 0, getCount(), "f'(x)")
            );
        }).start();
    }

    private void spinner_minStateChanged(ChangeEvent e) {
        spinner_max.setValue(Math.max(getMax(), getMin()));
        updateCount();
    }

    private void spinner_maxStateChanged(ChangeEvent e) {
        spinner_min.setValue(Math.min(getMin(), getMax()));
        updateCount();
    }

    private void spinner_epsStateChanged(ChangeEvent e) {
        updateCount();
    }

    private void updateCount() {
        spinner_count.setValue(getCount());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        label1 = new JLabel();
        txtFld_expression = new JTextField();
        separator1 = new JSeparator();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        lbl_count = new JLabel();
        separator2 = new JSeparator();
        btn_calculate = new JButton();
        spinner_min = new JSpinner();
        spinner_max = new JSpinner();
        spinner_eps = new JSpinner();
        spinner_count = new JSpinner();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Expression");

        //---- txtFld_expression ----
        txtFld_expression.setText("sin(1/x)");

        //---- label2 ----
        label2.setText("min");

        //---- label3 ----
        label3.setText("max");

        //---- label4 ----
        label4.setText("eps");

        //---- lbl_count ----
        lbl_count.setText("count");

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
        spinner_eps.setModel(new SpinnerNumberModel(0.01, null, null, 0.001));
        spinner_eps.addChangeListener(e -> spinner_epsStateChanged(e));

        //---- spinner_count ----
        spinner_count.setModel(new SpinnerNumberModel(2000, 1, null, 1));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(separator1, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                        .addComponent(separator2, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFld_expression, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinner_min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(label3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinner_max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(label4)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinner_eps, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                                                .addComponent(lbl_count)
                                                .addGap(18, 18, 18)
                                                .addComponent(spinner_count, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btn_calculate, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
                                .addContainerGap())
        );
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
                                        .addComponent(spinner_min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3)
                                        .addComponent(spinner_max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4)
                                        .addComponent(lbl_count)
                                        .addComponent(spinner_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinner_eps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_calculate)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel label1;
    private JTextField txtFld_expression;
    private JSeparator separator1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel lbl_count;
    private JSeparator separator2;
    private JButton btn_calculate;
    private JSpinner spinner_min;
    private JSpinner spinner_max;
    private JSpinner spinner_eps;
    private JSpinner spinner_count;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
