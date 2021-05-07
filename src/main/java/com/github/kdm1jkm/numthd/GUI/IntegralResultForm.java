/*
 * Created by JFormDesigner on Fri May 07 16:46:18 KST 2021
 */

package com.github.kdm1jkm.numthd.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author dm k
 */
public class IntegralResultForm extends JFrame {
    public IntegralResultForm(String expression, double min, double max, double result1, double result2) {
        initComponents();
        lbl_expression.setText(expression);
        lbl_min.setText(String.valueOf(min));
        lbl_max.setText(String.valueOf(max));
        lbl_result1.setText(String.valueOf(result1));
        lbl_result2.setText(String.valueOf(result2));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        label1 = new JLabel();
        lbl_expression = new JLabel();
        label3 = new JLabel();
        lbl_min = new JLabel();
        label5 = new JLabel();
        lbl_max = new JLabel();
        label7 = new JLabel();
        lbl_result1 = new JLabel();
        label9 = new JLabel();
        lbl_result2 = new JLabel();

        //======== this ========
        setTitle("Integral Result");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Integrate");

        //---- lbl_expression ----
        lbl_expression.setText("expression");

        //---- label3 ----
        label3.setText("from");

        //---- lbl_min ----
        lbl_min.setText("min");

        //---- label5 ----
        label5.setText("to");

        //---- lbl_max ----
        lbl_max.setText("max");

        //---- label7 ----
        label7.setText("The Trapezoidal Rule: ");

        //---- lbl_result1 ----
        lbl_result1.setText("result1");

        //---- label9 ----
        label9.setText("Simpson's Rule: ");

        //---- lbl_result2 ----
        lbl_result2.setText("result2");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbl_expression)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label3)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbl_min)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label5)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbl_max))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label7)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbl_result1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label9)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbl_result2)))
                    .addContainerGap(543, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(lbl_expression)
                        .addComponent(label3)
                        .addComponent(lbl_min)
                        .addComponent(label5)
                        .addComponent(lbl_max))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label7)
                        .addComponent(lbl_result1))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label9)
                        .addComponent(lbl_result2))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel label1;
    private JLabel lbl_expression;
    private JLabel label3;
    private JLabel lbl_min;
    private JLabel label5;
    private JLabel lbl_max;
    private JLabel label7;
    private JLabel lbl_result1;
    private JLabel label9;
    private JLabel lbl_result2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
