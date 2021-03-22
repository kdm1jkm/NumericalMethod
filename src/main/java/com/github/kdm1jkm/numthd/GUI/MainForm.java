/*
 * Created by JFormDesigner on Sun Mar 21 19:35:14 KST 2021
 */

package com.github.kdm1jkm.numthd.GUI;

import com.github.kdm1jkm.numthd.DrawableGraph;
import com.github.kdm1jkm.numthd.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author unknown
 */
public class MainForm extends JFrame {
    public MainForm() {
        initComponents();
    }

    private void numberOnlyTxtFld_KeyTyped(KeyEvent e) {
        if (Character.isDigit(e.getKeyChar()) || // 숫자이거나
                (!((JTextField) e.getComponent()).getText().contains(".") && e.getKeyChar() == '.') || // 소수점이거나(하나만 있어야함)
                ((JTextField) e.getComponent()).getText().length() == 0 && e.getKeyChar() == '-') { // 음수라면(첫 번째여야함)
            super.processKeyEvent(e);
            return;
        }
        e.consume();
    }

    private void txtFld_epsKeyTyped(KeyEvent e) {
        String result = (txtFld_eps.getText() + e.getKeyChar());
        double eps = Double.parseDouble(result);
        double max = getMax();
        double min = getMin();
        int count = (int) ((max - min) / eps) + 1;
        lbl_count.setText(String.format("count: %d", count));
    }

    private double getEps() {
        return Double.parseDouble(txtFld_eps.getText());
    }

    private double getMax() {
        return Double.parseDouble(txtFld_max.getText());
    }

    private double getMin() {
        return Double.parseDouble(txtFld_min.getText());
    }

    private int getCount() {
        return (int) ((getMax() - getMin()) / getEps()) + 1;
    }

    private void btn_calculate_clicked(ActionEvent e) {
        double[] xs = Main.getXs(getMin(), getMax(), getCount());
        double[] values = Main.getValues(txtFld_expression.getText(), xs);
        double[] diffs = Main.getDiffs(values, getEps());

        Main.drawGraph(
                new DrawableGraph(xs, values, 0, 0, getCount(), "f(x)"),
                new DrawableGraph(xs, diffs, 0, 0, getCount(), "f'(x)")
        );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        txtFld_expression = new JTextField();
        separator1 = new JSeparator();
        label2 = new JLabel();
        txtFld_min = new JTextField();
        label3 = new JLabel();
        txtFld_max = new JTextField();
        txtFld_eps = new JTextField();
        label4 = new JLabel();
        lbl_count = new JLabel();
        separator2 = new JSeparator();
        btn_calculate = new JButton();
        progressBar1 = new JProgressBar();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Expression");

        //---- label2 ----
        label2.setText("min");

        //---- txtFld_min ----
        txtFld_min.setText("0");
        txtFld_min.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberOnlyTxtFld_KeyTyped(e);
            }
        });

        //---- label3 ----
        label3.setText("max");

        //---- txtFld_max ----
        txtFld_max.setText("100");
        txtFld_max.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberOnlyTxtFld_KeyTyped(e);
            }
        });

        //---- txtFld_eps ----
        txtFld_eps.setText("0.01");
        txtFld_eps.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                numberOnlyTxtFld_KeyTyped(e);
                txtFld_epsKeyTyped(e);
            }
        });

        //---- label4 ----
        label4.setText("eps");

        //---- lbl_count ----
        lbl_count.setText("count: 10000");

        //---- btn_calculate ----
        btn_calculate.setText("Calculate!");
        btn_calculate.addActionListener(e -> btn_calculate_clicked(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(separator1, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                        .addComponent(separator2, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFld_expression, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFld_min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(label3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFld_max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(label4)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFld_eps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lbl_count))
                                        .addComponent(btn_calculate, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                                        .addComponent(progressBar1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
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
                                        .addComponent(txtFld_min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3)
                                        .addComponent(txtFld_max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_count)
                                        .addComponent(txtFld_eps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_calculate)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(progressBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JTextField txtFld_expression;
    private JSeparator separator1;
    private JLabel label2;
    private JTextField txtFld_min;
    private JLabel label3;
    private JTextField txtFld_max;
    private JTextField txtFld_eps;
    private JLabel label4;
    private JLabel lbl_count;
    private JSeparator separator2;
    private JButton btn_calculate;
    private JProgressBar progressBar1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
